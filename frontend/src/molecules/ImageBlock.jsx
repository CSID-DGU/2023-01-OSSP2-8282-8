import React from "react";
import styled from "styled-components";

const Img = styled.Image`
	width: 140px;
	height: 190px;
	resize: contain;
	box-sizing: border-box;
	margin: 0 35px;
`;

const ImageBlock = ({ img }) => {
	return (
		<Img
			source={{ uri: img }}
			style={{ width: 140, height: 190, resizeMode: "contain" }}
		/>
	);
};

export default ImageBlock;
