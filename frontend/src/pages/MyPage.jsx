import { useRef, useState } from "react";
import { Text } from "react-native";

import styled from "styled-components";

import CommunityButton from "../organisms/CommunityButton";
import CommunityInputContainer from "../organisms/CommunityInputContainer";

const Container = styled.View`
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
`;

const PDFCampusTypo = styled.Text`
	font-weight: 600;
	font-size: 40px;
`;

const Typoone = styled.Text`
	font-weight: 600;
	font-size: 35px;
`;

const Typotwo = styled.Text`
	font-weight: 300;
	font-size: 25px;
	line-height: 30px;
`;

const UserPicture = styled.View`	
	width: 150px;
	height: 150px;
	display: flex;
	justify-content: center;
	align-items: center;

	position: absolute;
	background: #D9D9D9;
	border-radius: 100px;
`;

const UserInfoContainer = styled.View`
	width: 640px;
	height: 205px;
	left: 89px;
	top: 212px;

	display: flex;
	flex-direction:row;
	justify-content: space-around;
	align-items: center;
	
	border: 2px solid #000000;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	border-radius: 20px;
`;

const SubInfoContainer = styled.View`
	width: 700px;
	height: 205px;
	left: 95px;
	top: 270px;

	display: flex;
	flex-direction:row;
	justify-content: flex-start;
	align-items: flex-start;
`;

const UserIntro = styled.View`
	justify-content: space-around;
	align-items : center;
`
const SubIntro = styled.View`
	justify-content: flex-start;
	align-items : flex-end;
`

const MyPage = () => {
	//const isSubscribed = false;
	
	return (
	<>
				<PDFCampusTypo>마이페이지</PDFCampusTypo>
				<UserInfoContainer>
					<UserIntro>
					<UserPicture></UserPicture>
					<Typoone>이름</Typoone>
					<Typotwo>안녕하세요 이현정입니다 21학번입니다...</Typotwo>
					</UserIntro>
				</UserInfoContainer>

				<SubInfoContainer>
					<Typoone>구독정보</Typoone>
					<SubIntro>
					<Typotwo>{'\n'}{'\n'}가입일{'\n'}구독일{'\n'}상품{'\n'}남은 기간</Typotwo>
					</SubIntro>
				</SubInfoContainer>
				</>
	);
};

export default MyPage;