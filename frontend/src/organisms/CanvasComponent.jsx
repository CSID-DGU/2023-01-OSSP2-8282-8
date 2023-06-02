import { SafeAreaView } from "react-native-safe-area-context";
import Canvas from "react-native-canvas";
import { useDeferredValue, useEffect, useRef, useState } from "react";
import {
	Alert,
	TouchableOpacity,
	View,
	useWindowDimensions,
	Dimensions,
} from "react-native";
import { Image } from "react-native";

import styled from "styled-components";

import pen_image from "../../assets/pen_image.png";
import eraser_image from "../../assets/eraser_image.png";
import highlight_image from "../../assets/highlight_image.png";
import prev_page from "../../assets/prev_page.png";
import next_page from "../../assets/next_page.png";
import postMetadata from "../../api/postMetadata";

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

const PageButtonContainer = styled.View`
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
	box-sizing: border-box;
	margin: 0 0 -13px 0;
`;

const Tool = ({ ctx, props, onClick }) => {
	return (
		<ToolIcon
			style={{ backgroundColor: props.color }}
			onPress={() => {
				onClick(ctx, props.type, props.color);
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

const ToolKit = ({ ctx }) => {
	const tools = [
		{
			color: "#F96060",
			type: "highlight",
		},
		{
			color: "#E1E440",
			type: "highlight",
		},
		{
			color: "#595FE5",
			type: "highlight",
		},
		{
			color: "#333",
			type: "pen",
		},
		{
			color: "#E12323",
			type: "pen",
		},
		{
			color: "#1E25BB",
			type: "pen",
		},
		{
			color: "white",
			type: "eraser",
		},
	];
	const toolOnClick = (ctx, type, color) => {
		ctx.strokeStyle = color;
		ctx.lineWidth = type == "pen" ? 2 : type == "highlight" ? 15 : 30;
	};
	return (
		<ToolKitContainer>
			{tools.map((tool) => (
				<Tool ctx={ctx} props={tool} onClick={toolOnClick} />
			))}
		</ToolKitContainer>
	);
};

const DownLoadButton = ({ onClick }) => {
	return (
		<DownLoadButtonStyle onPress={onClick}>
			<DownLoadTypo>필기 다운로드</DownLoadTypo>
		</DownLoadButtonStyle>
	);
};

const PageButton = () => {
	return (
		<PageButtonContainer>
			<Image source={prev_page} />
			<Image source={next_page} />
		</PageButtonContainer>
	);
};

const CanvasComponent = () => {
	const touchRef = useRef();
	const canvasRef = useRef();

	const [totalPath, setTotalPath] = useState([]);

	const [ctx, setCtx] = useState();

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
		if (path.length > 0) {
			setTotalPath((prev) => [...prev, path]);
			setPath([]);
		}
	};

	useEffect(() => {
		setTotalPath([]);
		setMeta([]);
		if (touchRef.current) {
			const ctx = canvasRef.current.getContext("2d");
			ctx.lineWidth = 2;
			const widthval = 1500;
			const heightval = 1000;
			if (ctx) {
				canvasRef.current.width = widthval;
				canvasRef.current.height = heightval;
				setCtx(ctx);
			}
		}
	}, []);

	const [path, setPath] = useState([]);
	const [meta, setMeta] = useState([]);

	const downloadOnClick = () => {
		totalPath.map((h) => {
			x_s = h.map((item) => item.x);
			y_s = h.map((item) => item.y);
			console.log("x_s", x_s);
			console.log("y_s", y_s);
			setMeta((prev) => [
				...prev,
				[
					Math.min(...x_s),
					Math.max(...x_s),
					y_s.reduce((a, b) => a + b, 0) / y_s.length,
				],
			]);
		});
		// postMetadata
		console.log("meta", meta);
	};

	return (
		<>
			<UpperContainer>
				<ToolKit ctx={ctx} />
				<View
					style={{
						display: "flex",
						flexDirection: "row",
						alignItems: "flex-end",
					}}
				>
					<DownLoadButton onClick={downloadOnClick} />
					<PageButton />
				</View>
			</UpperContainer>
			<View style={{ position: "relative", zIndex: 1 }}>
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

				<View
					style={{
						width: 100,
						height: 100,
						backgroundColor: "#000",
						position: "absolute",
						zIndex: -1,
					}}
				/>
			</View>
		</>
	);
};

export default CanvasComponent;
