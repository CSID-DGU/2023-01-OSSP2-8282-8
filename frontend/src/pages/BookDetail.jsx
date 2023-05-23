import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { View, Alert } from "react-native";

import Header from "../organisms/Header";
import UpperDetail from "../organisms/UpperDetail";
import LowerDetail from "../organisms/LowerDetail";
import getBookDetail from "../../api/getBookDetail";
import { useRecoilValue } from "recoil";
import { UserInfoState } from "../../state/UserInfoState";
import postAddBook from "../../api/postAddBook";
import Modal from "../organisms/Modal";

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
	margin: 34px 0;
`;
const BookTitleTypo = styled.Text`
	font-size: 50px;
	font-weight: 800;
	margin-left: 40px;
`;
const ContentContainer = styled.View`
	width: 80%;
	height: 60%;
	flex-direction: column;
	box-sizing: border-box;
	margin: 24px 0;
`;

const DetailInfoDivider = styled.View`
	width: 100%;
	height: 2px;
	background: #bebebe;
	box-sizing: border-box;
	margin-bottom: 17px;
`;

const BookDetail = ({ navigation, route }) => {
	const userId = useRecoilValue(UserInfoState).userId;
	const { id } = route.params;

	const [bookDetail, setBookDetail] = useState({});
	const handleBookDetail = (detail) => {
		setBookDetail(detail);
	};

	const handleAddBook = () => {
		setModalVisible(true);
	};

	const Move2Library = () => {
		navigation.navigate("MyLibrary");
	};

	const AddBookLibrary = () => {
		postAddBook(id, userId, handleAddBook);
	};

	useEffect(() => {
		getBookDetail(id, userId, handleBookDetail);
	}, []);

	const [modalVisible, setModalVisible] = useState(false);
	const handleModal = () => {
		setModalVisible(false);
	};
	return (
		<Container>
			<Modal
				typo="도서가 추가되었습니다."
				buttonTypo1="확인"
				visible={modalVisible}
				handleModal={handleModal}
			/>
			<Header navigation={navigation} />
			<BookTitleContainer>
				<BookTitleTypo>{bookDetail.bookTitle}</BookTitleTypo>
			</BookTitleContainer>
			<ContentContainer>
				<UpperDetail
					contentInfo={bookDetail}
					truepress={Move2Library}
					falsepress={AddBookLibrary}
					isBook={true}
				/>
				<DetailInfoDivider />
				<LowerDetail
					img1={bookDetail.bookCover}
					img2={bookDetail.bookCover}
					img3={bookDetail.bookCover}
					bookDetailContent={bookDetail.DetailInfo}
				/>
			</ContentContainer>
		</Container>
	);
};

export default BookDetail;
