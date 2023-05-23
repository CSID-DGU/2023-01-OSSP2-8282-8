import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { View, Alert, Modal } from "react-native";

import Header from "../organisms/Header";
import UpperDetail from "../organisms/UpperDetail";
import LowerDetail from "../organisms/LowerDetail";
import getNoteDetail from "../../api/getNoteDetail";
import { useRecoilValue } from "recoil";
import { UserInfoState } from "../../state/UserInfoState";
import postBuyNote from "../../api/postBuyNote";

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

const ModalContiner = styled.View`
	height: 100%;
	justify-content: center;
	align-items: center;
`;

const BlockTypo = styled.Text`
	font-size: 20px;
	font-weight: 800;
`;

const Block = styled.View`
	width: 400px;
	height: 300px;
	align-items: center;
	justify-content: space-around;
	border-radius: 20px;
	border-width: 2px;
	border-color: #ccc;
	background: white;
`;

const ButtonsContainer = styled.View`
	width: 80%;
	display: flex;
	flex-direction: row;
	justify-content: space-around;
`;
const ButtonWrapper = styled.TouchableOpacity`
	width: 140px;
	height: 66px;
	align-items: center;
	justify-content: center;
	border-radius: 11px;
	background: #56aaf6;
`;

const Buttontypo = styled.Text`
	font-size: 20px;
	font-weight: 800;
	color: white;
`;

const NoteDetail = ({ navigation, route }) => {
	const [noteDetail, setNoteDetail] = useState({});
	const handleNoteDetail = (detail) => {
		setNoteDetail(detail);
	};

	const userId = useRecoilValue(UserInfoState).userId;
	const { id } = route.params;
	useEffect(() => {
		getNoteDetail(id, userId, handleNoteDetail);
	}, []);

	const Move2Library = () => {
		navigation.navigate("MyLibrary");
	};

	const [modalVisible, setModalVisible] = useState(false);
	const handleBuyNote = () => {
		setModalVisible(true);
	};

	const buyNote = () => {
		postBuyNote(
			{
				userId: userId,
				noteId: id,
			},
			handleBuyNote
		);
	};

	return (
		<Container>
			<Modal visible={modalVisible} transparent={true}>
				<ModalContiner>
					<Block>
						<BlockTypo>필기가 구매되었습니다.</BlockTypo>
						<ButtonsContainer>
							<ButtonWrapper onPress={() => setModalVisible(false)}>
								<Buttontypo>확인</Buttontypo>
							</ButtonWrapper>
						</ButtonsContainer>
					</Block>
				</ModalContiner>
			</Modal>

			<Header navigation={navigation} />
			<BookTitleContainer>
				<BookTitleTypo>{noteDetail.noteTitle}</BookTitleTypo>
			</BookTitleContainer>
			<ContentContainer>
				<UpperDetail
					contentInfo={{
						bookCover: noteDetail.bookInfo?.bookCover,
						publicationYear: noteDetail.createdAt,
						modifiedDate: noteDetail.modifiedAt,
						isStored: noteDetail.isBought,
					}}
					truepress={Move2Library}
					falsepress={buyNote}
					isBook={false}
				/>
				<DetailInfoDivider />
				<LowerDetail
					img1={noteDetail.bookInfo?.bookCover}
					img2={noteDetail.bookInfo?.bookCover}
					img3={noteDetail.bookInfo?.bookCover}
					bookDetailContent={noteDetail.DetailInfo}
				/>
			</ContentContainer>
		</Container>
	);
};

export default NoteDetail;
