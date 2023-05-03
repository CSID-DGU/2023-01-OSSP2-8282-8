import { useRef, useState } from "react";
import { Text, Alert } from "react-native";

import styled from "styled-components";

import CommunityButton from "../organisms/CommunityButton";


const Container = styled.View`
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: column;
	justify-content: space-evenly;
	align-items: center;
`;

const TitleContainer1 = styled.View`
	width: 650px;
	height: 250px;
	display: flex;
	flex-direction: column;
	justify-content: space-evenly;
	align-items: center;
`;

const ListContainer1 = styled.View`
    width: 650px;
    height: 250px;
	display: flex;
	flex-direction: column;
	justify-content: space-evenly;
	align-items: center;
`;

const TitleContainer2 = styled.View`
    width: 650px;
    height: 250px;
	display: flex;
	flex-direction: column;
	justify-content: space-evenly;
	align-items: center;
`;

const ListContainer2 = styled.View`
    width: 650px;
    height: 250px;
	display: flex;
	flex-direction: column;
	justify-content: space-evenly;
	align-items: center;
`;

const PDFCampusTypo = styled.Text`
	font-weight: 600;
	font-size: 40px;
`;

const Line = styled.View`	
	width: 80%;
	height: 0px;

	border: 1px solid #BEBEBE;
`;


const MyLibrary = () => {

    return (
		<>
        <Container>
            <TitleContainer1>
			    <PDFCampusTypo>내가 구매한 필기</PDFCampusTypo>
            </TitleContainer1>
            <ListContainer1></ListContainer1>
                <Line></Line>

            <TitleContainer2>
                <PDFCampusTypo>나의 서재 리스트</PDFCampusTypo>
            </TitleContainer2>
            <ListContainer2></ListContainer2>
		</Container>
		</>
	);
};

export default MyLibrary;