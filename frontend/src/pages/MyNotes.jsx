import styled from "styled-components";

import Header from "../organisms/Header";
import NoteHandle from "../organisms/NoteHandle";
import { ScrollView, Alert, Modal } from "react-native";
import { useEffect, useState } from "react";
import getMyNotes from "../../api/getMyNotes";
import { useRecoilValue } from "recoil";
import { UserInfoState } from "../../state/UserInfoState";
import postDeleteNote from "../../api/postDeleteNote";

const Container = styled.View`
	display: flex;
`;
const Container2 = styled.View`
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

const NoteListContiner = styled.View`
	display: flex;
	width: 80%;
	justify-content: space-around;
	padding-bottom: 100px;
`;
const ButtonWrapper = styled.TouchableOpacity`
	width: 140px;
	height: 66px;
	align-items: center;
	justify-content: center;
	border-radius: 11px;
	background: #56aaf6;
`;
const ButtonWrapper2 = styled.TouchableOpacity`
	width: 140px;
	height: 66px;
	align-items: center;
	justify-content: center;
	border-radius: 11px;
	background: #d9d9d9;
`;
const Buttontypo = styled.Text`
	font-size: 20px;
	font-weight: 800;
	color: white;
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
const ModalContiner = styled.View`
	height: 100%;
	justify-content: center;
	align-items: center;
`;

const OnSale = () => {
	return Alert.alert("판매 등록");
};

const MyNotesList = ({ notes, handleModal1 }) => {
	return notes.map((note, i) => (
		<NoteHandle
			key={note.noteId}
			id={note.noteId}
			// img={note.bookInfo.bookCover}
			img={`https://pdfampus.s3.ap-northeast-2.amazonaws.com/${i + 1}.jpg`}
			bookTitle={note.noteTitle}
			isSaled={note.isSale}
			PublicationDate={note.bookInfo.publicationYear}
			ModifiedDate={note.modifiedAt}
			onPress1={OnSale}
			onPress2={handleModal1}
		/>
	));
};

const MyNotes = () => {
	const [modalVisible1, setModalVisible1] = useState(false);
	const [modalVisible2, setModalVisible2] = useState(false);
	const [notes, setNotes] = useState([]);
	const [selectedNote, setSelectedNote] = useState(-1);

	const handleModal1 = (select) => {
		setModalVisible1(true);
		setSelectedNote(select);
	};

	const handleNotes = (notes) => {
		setNotes(notes);
	};

	const userId = useRecoilValue(UserInfoState).userId;

	const DeleteNote = () => {
		setModalVisible1(false), setModalVisible2(true);
		postDeleteNote({ userId, selectedNote });
	};

	useEffect(() => {
		getMyNotes(userId, handleNotes);
		console.log("last selected:", selectedNote);
	}, []);
	return (
		<Container>
			<Modal visible={modalVisible1} transparent={true}>
				<ModalContiner>
					<Block>
						<BlockTypo>
							필기를 삭제하시겠습니까?{"\n"}*판매된필기는 삭제되지않습니다
						</BlockTypo>
						<ButtonsContainer>
							<ButtonWrapper onPress={DeleteNote}>
								<Buttontypo>삭제</Buttontypo>
							</ButtonWrapper>
							<ButtonWrapper2 onPress={() => setModalVisible1(false)}>
								<Buttontypo>취소</Buttontypo>
							</ButtonWrapper2>
						</ButtonsContainer>
					</Block>
				</ModalContiner>
			</Modal>
			<Modal visible={modalVisible2} transparent={true}>
				<ModalContiner>
					<Block>
						<BlockTypo>필기가 삭제되었습니다</BlockTypo>
						<ButtonsContainer>
							<ButtonWrapper onPress={() => setModalVisible2(false)}>
								<Buttontypo>확인</Buttontypo>
							</ButtonWrapper>
						</ButtonsContainer>
					</Block>
				</ModalContiner>
			</Modal>
			<Header />
			<ScrollView>
				<BookTitleContainer>
					<BookTitleTypo>나의 필기 관리</BookTitleTypo>
				</BookTitleContainer>
				<Container2>
					<NoteListContiner>
						<MyNotesList notes={notes} handleModal1={handleModal1} />
					</NoteListContiner>
				</Container2>
			</ScrollView>
		</Container>
	);
};

export default MyNotes;
