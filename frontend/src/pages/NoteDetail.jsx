import React from "react";
import styled from "styled-components";
import { View, Alert } from "react-native";

import CommunityHeader from "../organisms/CommunityHeader";
import NoteUpperDetail from "../organisms/NoteUpperDetail";
import LowerDetail from "../organisms/LowerDetail";

const Container =styled.View`
width:100%;
display: flex;

align-items:center;
`
const BookTitleContainer =styled.View`
width:100%;
height:auto;
display:flex;
justify-content:flex-start;
margin-bottom:20px;
`
const BookTitleTypo=styled.Text`
font-size:50px;
fontWeight:800;
margin-left:40px;
`
const ContentContainer=styled.View`
width:80%;
height:60%;
flex-direction:column;
`

const NoteInfo={
    bookTitle:"운영체제 필기자료",
    bookCover:'https://simage.mujikorea.net/goods/31/11/79/07/4550002435097_N_N_400.jpg',
    isStored:false,
    publicationDate:"0000년0월0일 오후 00:00",
    modifiedDate:"0000년0월0일 오후 00:00",
    DetailInfo:"상세정보입니다~~~~~"
}


const Move2Library = () => {
    return(
        Alert.alert("나의 필기로 이동")
    )
}

const AddBookLibrary = () => {
    return(
        Alert.alert("나의 필기에 추가")
    )
}

const BookDetail = () =>{
    return(
        <Container>
            <CommunityHeader/>
            <BookTitleContainer>
                <BookTitleTypo>{NoteInfo.bookTitle}</BookTitleTypo>
            </BookTitleContainer>
            <ContentContainer>
                <NoteUpperDetail img={ NoteInfo.bookCover}
                PublicationDate={NoteInfo.publicationDate} 
                ModifiedDate={NoteInfo.modifiedDate}
                isStored={NoteInfo.isStored} 
                truepress={Move2Library}
                falsepress={AddBookLibrary}
                />
                <LowerDetail 
                img1={NoteInfo.bookCover}
                img2={NoteInfo.bookCover}
                img3={NoteInfo.bookCover}
                bookDetailContent={NoteInfo.DetailInfo}/>
            </ContentContainer>
        </Container>
    )
}

export default BookDetail;