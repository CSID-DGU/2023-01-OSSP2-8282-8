import React from "react";
import styled from "styled-components";
import ImageBlock from "../molecules/ImageBlock";

const Container = styled.View`
	display: flex;
	align-items: center;
	justify-content: space-between;
	flex-direction: row;
`;

const PreviewContainer = styled.View`
	width: 69%;
	display: flex;
`;
const DetailContainer = styled.View`
	width: 29%;
	display: flex;
`;
const ImageContainer = styled.View`
	width: 100%;
	display: flex;
	background: #d9d9d9;
	flex-direction: row;
	justify-content: center;
	align-items: center;
`;
const TextContainer = styled.View`
	width: 100%;
	height: 250px;
	display: flex;
	background: #d9d9d9;
`;
const ImageListContainer = styled.View`
	width: 100%;
	height: 250px;
	background: #d9d9d9;
	flex-direction: row;
	display: flex;
	justify-content: space-around;
`;

const Titletypo = styled.Text`
	font-size: 20px;
	font-weight: 800;
	margin-bottom: 10px;
`;
const Detailtypo = styled.Text`
	font-size: 15px;
	font-weight: 800;
	margin: 4px;
`;

const LowerDetail = ({ img1, img2, img3, bookDetailContent }) => {
	return (
		<Container>
			<PreviewContainer>
				<Titletypo>미리보기</Titletypo>
				<ImageContainer>
					<ImageListContainer>
						<ImageBlock img={img1} />
						<ImageBlock img={img2} />
						<ImageBlock img={img3} />
					</ImageListContainer>
				</ImageContainer>
			</PreviewContainer>
			<DetailContainer>
				<Titletypo>상세정보</Titletypo>
				<TextContainer>
					<Detailtypo>{bookDetailContent}</Detailtypo>
				</TextContainer>
			</DetailContainer>
		</Container>
	);
};

export default LowerDetail;
