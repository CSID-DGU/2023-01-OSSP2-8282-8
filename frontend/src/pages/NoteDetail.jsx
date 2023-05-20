import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { View, Alert } from "react-native";

import Header from "../organisms/Header";
import UpperDetail from "../organisms/UpperDetail";
import LowerDetail from "../organisms/LowerDetail";
import getNoteDetail from "../../api/getNoteDetail";
import { useRecoilValue } from "recoil";
import { UserInfoState } from "../../state/UserInfoState";

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

const Move2Library = () => {
	return Alert.alert("나의 서재로 이동");
};

const AddBookLibrary = () => {
	return Alert.alert("나의 서재에 추가");
};

const NoteDetail = ({ navigation, route }) => {
	const [noteDetail, setNoteDetail] = useState({});
	const handleNoteDetail = (detail) => {
		setNoteDetail(detail);
	};

	const userId = useRecoilValue(UserInfoState).userId;
	useEffect(() => {
		const { id } = route.params;
		getNoteDetail(id, userId, handleNoteDetail);
	}, []);
	return (
		<Container>
			<Header navigation={navigation} />
			<BookTitleContainer>
				<BookTitleTypo>{noteDetail.bookTitle}</BookTitleTypo>
			</BookTitleContainer>
			<ContentContainer>
				<UpperDetail
					contentInfo={noteDetail}
					truepress={Move2Library}
					falsepress={AddBookLibrary}
					isBook={false}
				/>
				<DetailInfoDivider />
				<LowerDetail
					img1={noteDetail.bookCover}
					img2={noteDetail.bookCover}
					img3={noteDetail.bookCover}
					bookDetailContent={noteDetail.DetailInfo}
				/>
			</ContentContainer>
		</Container>
	);
};

export default NoteDetail;
