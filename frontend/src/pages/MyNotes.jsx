import styled from "styled-components";

import Header from "../organisms/Header";
import NoteHandle from "../organisms/NoteHandle";
import { ScrollView, Alert, Modal } from "react-native";
import { useState } from "react";

const Container=styled.View`
diaplay:flex;
`
const Container2=styled.View`
diaplay:flex;
align-items:center;
`

const BookTitleContainer = styled.View`
	width: 100%;
	height: auto;
	display: flex;
	justify-content: flex-start;
	margin: 34px 0;
`

const BookTitleTypo = styled.Text`
	font-size: 50px;
	font-weight: 800;
	margin-left: 40px;
`;

const NoteListContiner=styled.View`
display:flex;
width:80%;
justify-content:space-around;
padding-bottom: 100px;
`
const ButtonWrapper=styled.TouchableOpacity`
width:140px;
height:66px;
align-items:center;
justify-content:center;
border-radius:11px;
background:#56AAF6;
`
const ButtonWrapper2=styled.TouchableOpacity`
width:140px;
height:66px;
align-items:center;
justify-content:center;
border-radius:11px;
background:#D9D9D9;
`
const Buttontypo =styled.Text`
font-size:20px;
font-weight:800;
color:white;
`
const BlockTypo=styled.Text`
font-size:20px;
font-weight:800;
`

const Block=styled.View`
width:400px;
height:300px;
align-items:center;
justify-content:space-around;
border-radius:20px;
border-width: 2px;
border-color: #ccc;
background:white;
`
const ButtonsContainer=styled.View`
width:80%;
display:flex;
flex-direction:row;
justify-content:space-around;
`
const ModalContiner=styled.View`
height:100%;
justify-content:center;
align-items:center;

`

const notes = [...Array(10).keys()].map((id) => {
	return {
		id: id + 1,
		name: `필기자료${id + 1}`,
		image:
			"https://simage.mujikorea.net/goods/31/11/79/07/4550002435097_N_N_400.jpg",
		isSaled:false,
		PublicationDate:"0000년 00월 00일 오후 00:00",
		ModifiedDate:"0000년 00월 00일 오후 00:00"
	};
});

const OnSale = () => {
	return(
		Alert.alert("판매 등록")
	)
}


const MyNotes = () => {
	const [modalVisible1, setModalVisible1] = useState(false);
	const [modalVisible2, setModalVisible2] = useState(false);
	const MyNotesList =() =>{
		return(
			notes.map(note => (
				<NoteHandle
					key={note.id}
					img={note.image}
					bookTitle={note.name}
					isSaled={note.isSaled}
					PublicationDate={note.PublicationDate}
					ModifiedDate={note.ModifiedDate}
					onPress1={OnSale}
					onPress2={()=>setModalVisible1(true)}
				/>
			))
		)
	}
	const DeleteNote = () =>{
		return(
			setModalVisible1(false),
			setModalVisible2(true)
			//노트삭제 기능
		)
	}
    return(
        <Container>
				<Modal visible={modalVisible1} transparent={true}>
				<ModalContiner>
						<Block>
							<BlockTypo>필기를 삭제하시겠습니까?{"\n"}*판매된필기는 삭제되지않습니다</BlockTypo>
							<ButtonsContainer>
								<ButtonWrapper onPress={DeleteNote}>
									<Buttontypo>삭제</Buttontypo>
								</ButtonWrapper>
								<ButtonWrapper2 onPress={()=> setModalVisible1(false)}>
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
								<ButtonWrapper onPress={()=> setModalVisible2(false)}>
									<Buttontypo>삭제</Buttontypo>
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
				<MyNotesList />
			</NoteListContiner>
			</Container2>
			</ScrollView>
        </Container>
    )
}

export default MyNotes;