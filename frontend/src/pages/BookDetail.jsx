import React from "react";
import styled from "styled-components";
import { View, Alert } from "react-native";

import CommunityHeader from "../organisms/Header";
import UpperDetail from "../organisms/UpperDetail";
import LowerDetail from "../organisms/LowerDetail";

const Container = styled.View`
	width: 100%;
	display: flex;

	align-items: center;
`;
const BookTitleContainer = styled.View`
	width: 100%;
	height: auto;
	display: flex;
	justify-content: flex-start;
	margin-bottom: 20px;
`;
const BookTitleTypo = styled.Text`
	font-size: 50px;
	fontweight: 800;
	margin-left: 40px;
`;
const ContentContainer = styled.View`
	width: 80%;
	height: 60%;
	flex-direction: column;
`;

const BookInfo = {
	bookTitle: "운영체제",
	bookCover: "https://image.yes24.com/goods/89496122/XL",
	isStored: false,
	publicationDate: "0000년0월0일 오후 00:00",
	modifiedDate: "0000년0월0일 오후 00:00",
	DetailInfo: "상세정보입니다~~~~~",
};

const Move2Library = () => {
	return Alert.alert("나의 서재로 이동");
};

const AddBookLibrary = () => {
	return Alert.alert("나의 서재에 추가");
};

const BookDetail = () => {
	return (
		<Container>
			<CommunityHeader />
			<BookTitleContainer>
				<BookTitleTypo>{BookInfo.bookTitle}</BookTitleTypo>
			</BookTitleContainer>
			<ContentContainer>
				<UpperDetail
					img={BookInfo.bookCover}
					PublicationDate={BookInfo.publicationDate}
					ModifiedDate={BookInfo.modifiedDate}
					isStored={BookInfo.isStored}
					truepress={Move2Library}
					falsepress={AddBookLibrary}
				/>
				<LowerDetail
					img1={BookInfo.bookCover}
					img2={BookInfo.bookCover}
					img3={BookInfo.bookCover}
					bookDetailContent={BookInfo.DetailInfo}
				/>
			</ContentContainer>
		</Container>
	);
};

export default BookDetail;
