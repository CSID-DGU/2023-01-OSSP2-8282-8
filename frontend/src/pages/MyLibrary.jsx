import { useRef, useState } from "react";
import { Text, Alert } from "react-native";

import styled from "styled-components";

import CommunityButton from "../organisms/CommunityButton";
import ViewAllButton from "../organisms/ViewAllButton";
import Header from "../organisms/Header";
import ListContainer from "../organisms/ListContainer";

const Container = styled.View`
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: column;
	align-items: center;
	box-sizing: border-box;
	padding: 40px 0 0 0;
`;

const ContentWrapper = styled.View`
	width: 100%;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
`;

const ButtonWrapper = styled.View`
	width: 210px;
	height: 45px;
	box-sizing: border-box;
	margin: 0 0 8px 30px;
`;

const ViewAllButtonWrapper = styled.View`
	box-sizing: border-box;
	margin: 150px 150px 0 0;
`;

const TitleContainer = styled.View`
	width: 80%;
	height: 100px;
	display: flex;
	flex-direction: row;
	align-items: center;
`;

const ListWrapper = styled.View`
	width: 65%;
	display: flex;
	flex-direction: row;
	box-sizing: border-box;
	margin: 0 0 0 30px;
`;

const PDFCampusTypo = styled.Text`
	font-weight: 600;
	font-size: 40px;
`;

const Line = styled.View`
	width: 80%;
	height: 2px;
	background: #bebebe;
	box-sizing: border-box;
	margin: 25px 0 0 0;
`;

const SubOnClick = () => {
	Alert.alert("필기관리창");
};

const books = [...Array(4).keys()].map((id) => {
	return {
		id: id + 1,
		name: `운영체제${id + 1}`,
		image: "https://image.yes24.com/goods/89496122/XL",
	};
});

const notes = [...Array(4).keys()].map((id) => {
	return {
		id: id + 1,
		name: `필기자료${id + 1}`,
		image:
			"https://simage.mujikorea.net/goods/31/11/79/07/4550002435097_N_N_400.jpg",
	};
});

const MyLibrary = () => {
	return (
		<>
			<Header />

			<Container>
				<TitleContainer>
					<PDFCampusTypo>내가 구매한 필기</PDFCampusTypo>
					<ButtonWrapper>
						<CommunityButton typo="나의 필기 관리" onPress={SubOnClick} />
					</ButtonWrapper>
				</TitleContainer>
				<ContentWrapper>
					<ListWrapper>
						<ListContainer products={books} />
					</ListWrapper>
					<ViewAllButtonWrapper>
						<ViewAllButton typo="필기 전체보기" onPress={SubOnClick} />
					</ViewAllButtonWrapper>
				</ContentWrapper>
				<Line />
				<TitleContainer>
					<PDFCampusTypo>나의 서재 리스트</PDFCampusTypo>
				</TitleContainer>
				<ContentWrapper>
					<ListWrapper>
						<ListContainer products={notes} />
					</ListWrapper>
					<ViewAllButtonWrapper>
						<ViewAllButton typo="도서 전체보기" onPress={SubOnClick} />
					</ViewAllButtonWrapper>
				</ContentWrapper>
			</Container>
		</>
	);
};

export default MyLibrary;
