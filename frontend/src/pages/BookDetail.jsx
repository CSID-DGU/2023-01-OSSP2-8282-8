import React from "react";
import styled from "styled-components";
import CommunityHeader from "../organisms/CommunityHeader";
import { Image } from "react-native";

const Container =styled.View`
width:100%;
display: flex;
justify-content: center;
align-items:center;
`
const BookTitleContainer =styled.View`
width:100%;
height:auto;
display:flex;
justify-content:flex-start;
`
const BookTitleTypo=styled.Text`
font-size:50px;
fontWeight:800;
margin-left:40px;
`
const ContentContainer=styled.View`
width:70%;
height:60%;
flex-direction:column;
align-items:center
`
const FirstContainer=styled.View`
width:90%;
height:auto;
display:flex;
flex-direction:row;
`
const BookCover=({img})=>{
    return(
        <Container style={{width: "30%"}}>
            <Image
        source={{uri: img}}
        style={{ width: 130, height: 190, resizeMode: 'contain',  }}
      />
        </Container>
    )
}
const DateInfo=({date1,date2})=>{
    return(
        <Container style={{width: "50%"}}>

        </Container>
    )
}

const BookInfo={
    bookTitle:"도서 컨텐츠명",
    bookCover:"link",
    isStored:false,
    publicationDate:"0000년0월0일 오후 00:00",
    modifiedDate:"0000년0월0일 오후 00:00",
    DetailInfo:"상세정보입니다~~~~~"
}

const BookDetail = () =>{
    return(
        <Container>
            <CommunityHeader/>
            <BookTitleContainer>
                <BookTitleTypo>{BookInfo.bookTitle}</BookTitleTypo>
            </BookTitleContainer>
            <ContentContainer>
                <FirstContainer>
                    <BookCover img="https://image.yes24.com/goods/89496122/XL"/>
                </FirstContainer>
            </ContentContainer>
        </Container>
    )
}

export default BookDetail;