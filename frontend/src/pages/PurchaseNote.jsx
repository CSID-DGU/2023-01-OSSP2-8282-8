import { useRef, useState } from "react";
import { Text, Alert } from "react-native";

import styled from "styled-components";

import CommunityHeader from "../organisms/CommunityHeader";
import PurchaseButton2 from "../organisms/PurchaseButton2";

const Container = styled.View`
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: cloumn;
	justify-content: flex-first;
	align-items: center;
`;

const PicContainer = styled.View`
	width: 50%;
	height: 35%;
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	align-items: flex-end;
`;

const PicTextContainer = styled.View`
	width: 50%;
	height: 5%;
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	align-items: center;
`;

const InfoContainer = styled.View`
	width: 100%;
	height: 25%;
	display: flex;
	flex-direction: cloumn;
	justify-content: space-around;
	align-items: center;
`;

const TextContainer = styled.View`
	width: 50%;
	height: 25%;
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	align-items: flex-end;
`;


const ButtonContainer = styled.View`
	width: 30%;
	height: 15%;
	display: flex;
	flex-direction: cloumn;
	justify-content: center;
	align-items: center;
`;

const Pic = styled.View`
	box-sizing: border-box;
	width: 140px;
	height: 190px;

	background: #FFFFFF;
	border: 1px solid #000000;
`;

const Line = styled.View`	
	width: 50%;
	height: 0px;
	border: 1px solid #BEBEBE;
`;

const Typo = styled.Text`
	font-weight: 600;
	font-size: 20px;
	color: #000000;
`;

const NoteInfo = {
	code:"필기",
	writer:"이현정",
	createdAt:"2023-01-01",
	modifiedAt:"2023-05-01",
	price:"2000",
	isBought:true,
	NoteCover:"",
	BookInfo:{
		bookTitle:"운영체제",
		author:"JeongJunHo",
		publisher:"퍼스트북",
		publicationYear:1,
	}
}

const PurchaseSub = () => {

	const purchase = () => {
		Alert.alert("결제 완료");
	}

	return (
			<>	
			<CommunityHeader/>
				<Container>
					<PicContainer>
						<Pic></Pic>
						<Pic></Pic>
					</PicContainer>
					<PicTextContainer>
						<Text>{NoteInfo.BookInfo.bookTitle}</Text>
						<Text>미리보기</Text>
					</PicTextContainer>

					<InfoContainer>
						<TextContainer>
							<Typo>작성자</Typo>
							<Typo>{NoteInfo.writer}</Typo>
						</TextContainer>
						<Line></Line>
						
						<TextContainer>
							<Typo>최근 수정 날짜</Typo>
							<Typo>{NoteInfo.modifiedAt}</Typo>
						</TextContainer>
						<Line></Line>

						<TextContainer>
							<Typo>결제 가격</Typo>
							<Typo>{NoteInfo.price}원</Typo>
						</TextContainer>
						<Line></Line>

					</InfoContainer>

					<ButtonContainer>
						<PurchaseButton2 typo="결제하기" onPress={purchase} />			
					</ButtonContainer>
					
				</Container>
			</>
	);
	
};

export default PurchaseSub;