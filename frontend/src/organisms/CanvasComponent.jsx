import { SafeAreaView } from "react-native-safe-area-context";
import Canvas, { Image as CanvasImage } from "react-native-canvas";
import { useEffect, useRef, useState } from "react";
import { TouchableOpacity, View } from "react-native";
import { Image } from "react-native";

import styled from "styled-components";

import pen_image from "../../assets/pen_image.png";
import eraser_image from "../../assets/eraser_image.png";
import highlight_image from "../../assets/highlight_image.png";
import prev_page from "../../assets/prev_page.png";
import next_page from "../../assets/next_page.png";
import postMetadata from "../../api/postMetadata";

import { useRecoilValue } from "recoil";
import { UserInfoState } from "../../state/UserInfoState";
import postSaveNote from "../../api/postSaveNote";

import CustomedModal from "./Modal";

const UpperContainer = styled.View`
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: flex-end;
	box-sizing: border-box;
	margin: 13px 0 13px 0;
`;

const ToolKitContainer = styled.View`
	width: 325px;
	height: 56px;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
	border-radius: 10px;
	background: #999;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
`;

const ToolIcon = styled.TouchableOpacity`
	width: 36px;
	height: 36px;
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 50%;
	box-sizing: border-box;
	margin: 0 4px;
`;

const DownLoadButtonStyle = styled.TouchableOpacity`
	width: 119px;
	height: 35px;
	display: flex;
	justify-content: center;
	align-items: center;
	background: #281696;
	border-radius: 8px;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
`;

const DownLoadTypo = styled.Text`
	color: #fff;
	font-size: 16px;
	font-weight: 600;
`;

const PageButtonContainer = styled.TouchableOpacity`
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
	box-sizing: border-box;
	margin: 0 0 -13px 0;
`;

const PageNumberWrapper = styled.View`
	width: 71px;
	height: 35px;
	display: flex;
	justify-content: center;
	align-items: center;
	background: #d9d9d9;
	box-sizing: border-box;
	margin: 0 12px;
	border-radius: 8px;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
`;

const PageNumberValue = styled.Text`
	font-size: 16px;
	font-weight: 600;
	color: #ffffff;
	text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
`;

const Tool = ({ ctx, props, onClick }) => {
	return (
		<ToolIcon
			style={{ backgroundColor: props.color[0] }}
			onPress={() => {
				onClick(ctx, props.type, props.color[1]);
				props.type == "eraser"
					? (ctx.globalCompositeOperation = "destination-out")
					: (ctx.globalCompositeOperation = "source-over");
			}}
		>
			<Image
				source={
					props.type == "pen"
						? pen_image
						: props.type == "highlight"
						? highlight_image
						: eraser_image
				}
				style={{ width: 26, height: 26, resizeMode: "contain" }}
			/>
		</ToolIcon>
	);
};

const ToolKit = ({ hidden, ctx }) => {
	const tools = [
		{
			color: ["rgb(249,96,96)", "rgba(249,96,96,0.05)"],
			type: "highlight",
		},
		{
			color: ["rgb(225,228,64)", "rgba(225,228,64,0.05)"],
			type: "highlight",
		},
		{
			color: ["rgb(89,95,229)", "rgba(89,95,229,0.05)"],
			type: "highlight",
		},
		{
			color: ["#333", "#333"],
			type: "pen",
		},
		{
			color: ["#E12323", "#E12323"],
			type: "pen",
		},
		{
			color: ["#1E25BB", "#1E25BB"],
			type: "pen",
		},
		{
			color: ["white", "white"],
			type: "eraser",
		},
	];
	const toolOnClick = (ctx, type, color) => {
		ctx.strokeStyle = color;
		ctx.lineWidth = type == "pen" ? 2 : type == "highlight" ? 15 : 30;
	};
	return (
		<ToolKitContainer
			style={{ opacity: hidden ? 0 : null, height: hidden ? 0 : 56 }}
		>
			{tools.map((tool) => (
				<Tool ctx={ctx} props={tool} onClick={toolOnClick} />
			))}
		</ToolKitContainer>
	);
};

const DownLoadButton = ({ hidden, onClick }) => {
	return (
		<DownLoadButtonStyle
			style={{ opacity: hidden ? 0 : null, height: hidden ? 0 : 35 }}
			onPress={onClick}
		>
			<DownLoadTypo>필기 다운로드</DownLoadTypo>
		</DownLoadButtonStyle>
	);
};

const PageButton = ({ prevOnClick, nextOnClick }) => {
	return (
		<PageButtonContainer>
			<TouchableOpacity onPress={prevOnClick}>
				<Image source={prev_page} />
			</TouchableOpacity>
			<TouchableOpacity onPress={nextOnClick}>
				<Image source={next_page} />
			</TouchableOpacity>
		</PageButtonContainer>
	);
};

const PageNumber = ({ number, totalPage }) => {
	return (
		<PageNumberWrapper>
			<PageNumberValue>
				{number}/{totalPage}
			</PageNumberValue>
		</PageNumberWrapper>
	);
};

const CanvasComponent = ({
	bookId,
	content,
	prevOnClick,
	nextOnClick,
	currentPage,
	isNote,
}) => {
	const touchRef = useRef();
	const canvasRef = useRef();

	const userId = useRecoilValue(UserInfoState).userId;

	const [totalPath, setTotalPath] = useState([]);

	const [ctx, setCtx] = useState();

	const [img, setImg] = useState({});

	const handlePrev = async () => {
		prevOnClick();

		const url = await canvasRef.current.toDataURL("image/png");
		setImg({ ...img, [currentPage + 1]: url });

		setTotalPath([]);
		ctx.clearRect(0, 0, canvasRef.current.width, canvasRef.current.height);
	};

	const handleNext = async () => {
		nextOnClick();

		const url = await canvasRef.current.toDataURL("image/png");
		setImg({ ...img, [currentPage + 1]: url });

		setTotalPath([]);
		ctx.clearRect(0, 0, canvasRef.current.width, canvasRef.current.height);
	};

	const handleTouchStart = (event) => {
		const x = event.nativeEvent.locationX;
		const y = event.nativeEvent.locationY;
		ctx.beginPath();
		ctx.lineTo(x, y);
		ctx.stroke();
		if (ctx.lineWidth == 15) {
			setPath([{ x, y }]);
		}
	};

	const handleTouchMove = (event) => {
		const x = event.nativeEvent.locationX;
		const y = event.nativeEvent.locationY;
		ctx.lineTo(x, y);
		ctx.stroke();
		if (ctx.lineWidth == 15) {
			setPath((prevPath) => [...prevPath, { x, y }]);
		}
	};

	const handleTouchEnd = () => {
		let md = {};
		const ttp = [...totalPath, path];
		if (path.length > 0) {
			setTotalPath((prev) => [...prev, path]);
			setPath([]);
		}
		ttp.map((h) => {
			const x_s = h.map((item) => item.x);
			const y_s = h.map((item) => item.y);

			const position = [
				Math.min(...x_s),
				Math.max(...x_s),
				y_s.reduce((a, b) => a + b, 0) / y_s.length,
			];
			const idx = position[0] > 590 ? currentPage + 2 : currentPage + 1;
			if (!md[[idx]]) {
				md[[idx]] = [];
			}

			md[[idx]].push(
				position[0] > 590
					? [position[0] - 590, position[1] - 590, position[2]]
					: position
			);

			setMeta({ ...meta, ...md });
		});
	};

	useEffect(() => {
		setTotalPath([]);
		setMeta([]);
		setMetadata({});
		setRowNums({});
		setImg({});
		if (touchRef.current) {
			const ctx = canvasRef.current.getContext("2d");
			ctx.lineWidth = 2;
			const widthval = 1180;
			const heightval = 680;
			if (ctx) {
				canvasRef.current.width = widthval;
				canvasRef.current.height = heightval;
				setCtx(ctx);
			}
		}
	}, []);

	const [path, setPath] = useState([]);
	const [meta, setMeta] = useState({});
	const [rowNums, setRowNums] = useState({});

	const handleRowNums = (data) => {
		setRowNums(data);
	};

	const [metadata, setMetadata] = useState();
	const handleMetadata = (data) => {
		setMetadata(data);
	};

	const downloadOnClick = async () => {
		const { metadatas, rowNums } = await postMetadata(
			bookId,
			{
				data: {
					metadata: meta,
				},
			},
			handleMetadata,
			handleRowNums
		);
		let newUrl = {};
		const getBase64img = async (imgUrl, metadata, rowNums, index) => {
			await ctx.clearRect(
				0,
				0,
				canvasRef.current.width,
				canvasRef.current.height
			);

			ctx.font = "8px Arial";

			let img = new CanvasImage(canvasRef.current);
			img.src = imgUrl?.slice(1, -1);

			img.addEventListener("load", async () => {
				ctx.drawImage(img, 0, 0, 1180, 680);

				rowNums[0].map((r) => {
					const { rowNum, rowY } = r;
					ctx.fillText(rowNum - 1, 10, rowY);
				});
				rowNums[1]?.map((r) => {
					const { rowNum, rowY } = r;
					ctx.fillText(rowNum - 1, 600, rowY);
				});

				metadata[0]?.map((m) => {
					ctx.fillText(
						m.textInfo.extractedText,
						m.positionInfo[0],
						m.positionInfo[2]
					);
					ctx.fillStyle = "#666";
					ctx.fillText(
						2 * index - 1 + "쪽 " + m.textInfo.rowNumber + "행",
						m.positionInfo[0],
						m.positionInfo[2] - 15
					);
					ctx.fillStyle = "#000";
				});
				metadata[1]?.map((m) => {
					ctx.fillText(
						m.textInfo.extractedText,
						m.positionInfo[0] + 590,
						m.positionInfo[2]
					);
					ctx.fillStyle = "#666";
					ctx.fillText(
						2 * index + "쪽 " + m.textInfo.rowNumber + "행",
						m.positionInfo[0] + 590,
						m.positionInfo[2] - 15
					);
					ctx.fillStyle = "#000";
				});
				ctx.fillText(2 * index - 1, 20, 660);
				ctx.fillText(2 * index, 1160, 660);

				const newURL = await canvasRef.current.toDataURL("image/png");
				newUrl[[index]] = newURL.slice(1, -1);

				await ctx.clearRect(
					0,
					0,
					canvasRef.current.width,
					canvasRef.current.height
				);

				return;
			});
			rowNums[0].map((r) => {
				const { rowNum, rowY } = r;
				ctx.fillText(rowNum - 1, 10, rowY);
			});
			rowNums[1]?.map((r) => {
				const { rowNum, rowY } = r;
				ctx.fillText(rowNum - 1, 600, rowY);
			});

			metadata[0]?.map((m) => {
				ctx.fillText(
					m.textInfo.extractedText,
					m.positionInfo[0],
					m.positionInfo[2]
				);
				ctx.fillStyle = "#666";
				ctx.fillText(
					2 * index - 1 + "쪽 " + m.textInfo.rowNumber + "행",
					m.positionInfo[0],
					m.positionInfo[2] - 15
				);
				ctx.fillStyle = "#000";
			});
			metadata[1]?.map((m) => {
				ctx.fillText(
					m.textInfo.extractedText,
					m.positionInfo[0] + 590,
					m.positionInfo[2]
				);
				ctx.fillStyle = "#666";
				ctx.fillText(
					2 * index + "쪽 " + m.textInfo.rowNumber + "행",
					m.positionInfo[0] + 590,
					m.positionInfo[2] - 15
				);
				ctx.fillStyle = "#000";
			});
			ctx.fillText(2 * index - 1, 20, 660);
			ctx.fillText(2 * index, 1160, 660);

			const newURL = await canvasRef.current.toDataURL("image/png");
			newUrl[[index]] = newURL.slice(1, -1);
			await ctx.clearRect(
				0,
				0,
				canvasRef.current.width,
				canvasRef.current.height
			);
		};
		const len = Math.floor(content.length / 2) + 1;

		for (let i = 1; i <= len; i++) {
			await getBase64img(
				img[[2 * i - 1]],
				metadatas ? [metadatas[[2 * i - 1]], metadatas[[2 * i]]] : null,
				[rowNums[[2 * i - 1]], rowNums[[2 * i]]],
				i
			);
		}

		const noteSaveDTO = {
			userId: userId.toString(),
			bookId: bookId.toString(),
			note: newUrl,
		};
		const successed = await postSaveNote(noteSaveDTO);
		setModalVisible(successed);
	};

	const [modalVisible, setModalVisible] = useState(false);
	const handleModal = () => {
		setModalVisible(false);
	};
	return (
		<>
			<CustomedModal
				typo="필기가 저장되었습니다."
				buttonTypo1="확인"
				visible={modalVisible}
				handleModal={handleModal}
			/>
			<UpperContainer>
				<ToolKit hidden={isNote} ctx={ctx} />
				<View
					style={{
						display: "flex",
						flexDirection: "row",
						alignItems: "flex-end",
					}}
				>
					<DownLoadButton hidden={isNote} onClick={downloadOnClick} />
					<PageNumber
						number={currentPage + 1}
						totalPage={isNote ? content.length * 2 : content.length}
					/>
					<PageButton prevOnClick={handlePrev} nextOnClick={handleNext} />
				</View>
			</UpperContainer>
			<View style={{ position: "relative", zIndex: 1 }}>
				{!isNote ? (
					<View
						ref={touchRef}
						style={{
							backgroundColor: "transparent",
							width: "100%",
							height: "100%",
							display: "flex",
							justifyContent: "center",
							alignItems: "center",
							borderStyle: "solid",
							borderWidth: "1px",
							borderColor: "#D9D9D9",
							borderRadius: "15",
							boxSizing: "border-box",
						}}
						onTouchStart={handleTouchStart}
						onTouchMove={handleTouchMove}
						onTouchEnd={handleTouchEnd}
					>
						<Canvas
							ref={canvasRef}
							style={{
								width: "100%",
								height: "100%",
								backgroundColor: "transparent",
							}}
						/>
					</View>
				) : null}
				<View
					style={{
						display: "flex",
						flexDirection: "row",
						position: "absolute",
						zIndex: -1,
					}}
				>
					<Image
						source={{
							uri: isNote
								? content[Math.floor(currentPage / 2)]
								: content[currentPage],
						}}
						style={{
							width: isNote ? 1180 : 590,
							height: 680,
						}}
					/>
					{currentPage + 1 < content.length && !isNote ? (
						<Image
							source={{
								uri: content[currentPage + 1],
							}}
							style={{
								width: 590,
								height: 680,
							}}
						/>
					) : null}
				</View>
			</View>
		</>
	);
};

export default CanvasComponent;
