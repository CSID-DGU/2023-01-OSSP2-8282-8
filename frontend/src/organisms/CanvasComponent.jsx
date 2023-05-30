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

const Tool = ({ ctx, props, onClick }) => {
	const typo = props.type + "_" + props.color;
	return (
		<ToolIcon
			style={{ backgroundColor: props.color }}
			onPress={() => {
				onClick(ctx, props.type, props.color);
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
	const [selectedTool, setSelectedTool] = useState("pen_black");
	const toolOnClick = (ctx, type, color) => {
		ctx.strokeStyle = color;
		ctx.lineWidth = type == "pen" ? 2 : 15;
		setSelectedTool(type + "_" + color);
	};
	return (
		<ToolKitContainer>
			{tools.map((tool) => (
				<Tool ctx={ctx} props={tool} onClick={toolOnClick} />
			))}
		</ToolKitContainer>
	);
};

const CanvasComponent = () => {
	const touchRef = useRef();
	const canvasRef = useRef();

	const [ctx, setCtx] = useState();

	const handleTouchStart = (event) => {
		// Start a new path when the user touches the canvas
		const x = event.nativeEvent.locationX;
		const y = event.nativeEvent.locationY;
		console.log("touch start:", x, y);
		ctx.beginPath();
		ctx.lineTo(x, y);
		ctx.stroke();
		setPath([{ x, y }]);
	};

	const handleTouchMove = (event) => {
		// Update the path while the user moves their finger
		const x = event.nativeEvent.locationX;
		const y = event.nativeEvent.locationY;
		console.log("touch move:", x, y);
		ctx.lineTo(x, y);
		ctx.stroke();
		setPath((prevPath) => [...prevPath, { x, y }]);
	};

	const handleTouchEnd = () => {
		// Finish the path when the user releases their finger
		setPath([]);
	};

	useEffect(() => {
		if (touchRef.current) {
			const ctx = canvasRef.current.getContext("2d");
			const widthval = 1500;
			const heightval = 1000;
			if (ctx) {
				canvasRef.current.width = widthval;
				canvasRef.current.height = heightval;

				console.log("current:", canvasRef.current);
				setCtx(ctx);
			}
		}
	}, []);

	const [path, setPath] = useState([]);

	return (
		<>
			<SafeAreaView style={{ flex: 1 }}>
				<ToolKit ctx={ctx} />
				<View
					ref={touchRef}
					style={{
						backgroundColor: "transparent",
						width: "100%",
						height: "100%",
						display: "flex",
						justifyContent: "center",
						alignItems: "center",
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
			</SafeAreaView>
		</>
	);
};

export default CanvasComponent;
