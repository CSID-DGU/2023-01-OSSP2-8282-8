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
	justify-content: center;
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
	height: 25%;
	display: flex;
	flex-direction: cloumn;
	justify-content: space-around;
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

const TitleTypo = styled.Text`
	font-weight: 700;
	font-size: 40px;
	color: #000000;
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

	const gotoMain = () => {
		Alert.alert("메인페이지로 이동");
	}

	const gotoMyLibrary = () => {
		Alert.alert("나의 서재로 이동");
	}

	return (
			<>	
			<CommunityHeader/>
				<Container>
					<TitleTypo>결제가 완료 되었습니다!</TitleTypo>
					<InfoContainer>
						<TextContainer>
							<Typo>결제상품</Typo>
							<Typo>{NoteInfo.code}</Typo>
						</TextContainer>
						<Line></Line>
						
						<TextContainer>
							<Typo>결제액수</Typo>
							<Typo>{NoteInfo.price}원</Typo>
						</TextContainer>
						<Line></Line>

						<TextContainer>
							<Typo>결제수단</Typo>
							<Typo>0000</Typo>
						</TextContainer>
						<Line></Line>

					</InfoContainer>

					<ButtonContainer>
						<PurchaseButton2 typo="메인페이지로 이동" onPress={gotoMain} />
						<PurchaseButton2 typo="나의 서재로 이동" onPress={gotoMyLibrary} />		
					</ButtonContainer>
					
				</Container>
			</>
	);
	
};

export default PurchaseSub;