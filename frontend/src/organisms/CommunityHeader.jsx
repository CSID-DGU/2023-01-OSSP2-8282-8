import React from "react";
import styled from "styled-components";
import { Alert,TouchableOpacity } from "react-native";

const Container =styled.View`
display: flex;
justify-content: center;
align-items: center;
`
const HeaderContainer = styled.View`
    width: 100%;
    height: 50px;
    background-color: #56AAF6;
    display: flex;
    margin-top:20px;
    flex-direction:row;
    align-items:center;
    justify-contnent:space-between;
`
const ButtonContainer =styled.View`
width:80%;
background-color: #56AAF6;
display: flex;
flex-direction:row;
align-items:center;
justify-content:flex-end;
`
const TitleTypo = styled.Text`
    font-size:20px;
    color: white;
    margin-left:20px;
    fontWeight:800;
`
const ButtonTypo=styled.Text`
    font-size:15px;
    color: white;
    margin-left:20px;
`
const TitleButton = ({typo, onPress}) =>{
    return(
        <Container>
            <TouchableOpacity onPress={onPress}>
                <ButtonTypo>{typo}</ButtonTypo>
            </TouchableOpacity>
        </Container>
    )
}

const ClickHandelMyPage =() => {
    return(Alert.alert("마이페이지로 이동"))
}
const ClickHandelMyLibrary =() => {
    return(Alert.alert("나의서재로 이동"))
}
const ClickHandelLogout =() => {
    return(Alert.alert("로그아웃"))
}


const CommunityHeader = () => {
    return (
           <HeaderContainer>
                <TitleTypo>PDFCampus</TitleTypo>
                <ButtonContainer>
                    <TitleButton typo='마이페이지' onPress={ClickHandelMyPage}/>
                    <TitleButton typo='나의 서재' onPress={ClickHandelMyLibrary}/>
                    <TitleButton typo='로그아웃' onPress={ClickHandelLogout}/>
                </ButtonContainer>
           </HeaderContainer>
    )
}

export default CommunityHeader;
