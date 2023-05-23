import React from "react";
import { Text, Alert } from "react-native";

import styled from "styled-components";

import CommunityHeader from "../organisms/CommunityHeader";
import PurchaseButton from "../organisms/PurchaseButton";

const Container = styled.View`
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: cloumn;
	justify-content: flex-first;
	align-items: center;
`;

const TextContainer = styled.View`
	width: 30%;
	height: 30%;
	display: flex;
	flex-direction: cloumn;
	justify-content: flex-end;
	align-items: flex-first;
`;

const ButtonContainer = styled.View`
	width: 30%;
	height: 35%;
	display: flex;
	flex-direction: cloumn;
	justify-content: space-around;
	align-items: flex-first;
`;

const TextTypo = styled.Text`
	font-weight: 600;
	font-size: 20px;
	align-items: center;
`;


const PurchaseSub = () => {

	const purchase = () => {
		Alert.alert("결제 완료");
	}

	return (
			<>	
			<CommunityHeader/>
				<Container>
				
					<TextContainer>
						<TextTypo>* 등록된 교재 PDF 파일 무제한 다운로드</TextTypo>
						<TextTypo>* 다른 사용자의 필기자료 구매가능</TextTypo>
					</TextContainer>

					<ButtonContainer>
						<PurchaseButton typo="0000원/3개월" onPress={purchase} />
						<PurchaseButton typo="0000원/6개월" onPress={purchase} />
					</ButtonContainer>
				</Container>
			</>
	);
	
};

export default PurchaseSub;
