import React from "react";
import styled from "styled-components";
import { Alert, TouchableOpacity } from "react-native";

const Container = styled.View`
	display: flex;
	justify-content: center;
	align-items: center;
`;
const HeaderContainer = styled.View`
	width: 100%;
	height: 60px;
	background-color: #56aaf6;
	display: flex;
	margin-top: 20px;
	flex-direction: row;
	align-items: center;
	justify-content: space-between;
	box-sizing: border-box;
	margin: 0;
	padding: 24px 15px 15px 15px;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
`;
const ButtonContainer = styled.View`
	width: 80%;
	background-color: #56aaf6;
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: flex-end;
`;
const TitleTypo = styled.Text`
	font-size: 20px;
	color: white;
	font-weight: 800;
`;
const ButtonTypo = styled.Text`
	font-size: 15px;
	color: #fff;
	margin: 0 15px;
`;
const TitleButton = ({ typo, onPress }) => {
	return (
		<Container>
			<TouchableOpacity onPress={onPress}>
				<ButtonTypo>{typo}</ButtonTypo>
			</TouchableOpacity>
		</Container>
	);
};

const CommunityHeader = ({ navigation }) => {
	const ClickHome = () => {
		navigation.navigate("MainPage");
	};

	const ClickHandelMyPage = () => {
		navigation.navigate("MyPage");
	};
	const ClickHandelMyLibrary = () => {
		navigation.navigate("MyLibrary");
	};
	const ClickHandelLogout = () => {
		navigation.navigate("LogIn");
	};

	return (
		<HeaderContainer>
			<TouchableOpacity onPress={ClickHome}>
				<TitleTypo>PDFCampus</TitleTypo>
			</TouchableOpacity>
			<ButtonContainer>
				<TitleButton typo="마이페이지" onPress={ClickHandelMyPage} />
				<TitleButton typo="나의 서재" onPress={ClickHandelMyLibrary} />
				<TitleButton typo="로그아웃" onPress={ClickHandelLogout} />
			</ButtonContainer>
		</HeaderContainer>
	);
};

export default CommunityHeader;
