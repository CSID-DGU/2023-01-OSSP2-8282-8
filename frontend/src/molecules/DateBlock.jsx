import React from "react";
import styled from "styled-components";

const Container =styled.View`
width:40%;
display: flex;
justify-content: space-around;
align-items:center;
flex-direction:column;
`
const DateContainer=styled.View`
display:flex;
flex:1
`
const DateInfoTypo =styled.Text`
    font-size:20px;
    color: black;
    fontweight:900;
`


const DateBlock = ({PublicationDate, ModifiedDate}) =>{
    return (
        <Container>
            <DateContainer>
                <DateInfoTypo>등록날짜</DateInfoTypo>
                <DateInfoTypo>{PublicationDate}</DateInfoTypo>
            </DateContainer>
            <DateContainer>
                <DateInfoTypo>최근 수정 날짜</DateInfoTypo>
                <DateInfoTypo>{ModifiedDate}</DateInfoTypo>
            </DateContainer>
        </Container>
    )
}

export default DateBlock;