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

const ClickHome = (navigation) => {
	navigation.navigate("MainPage");
};

const ClickHandelMyPage = (navigation) => {
	navigation.navigate("MyPage");
};
const ClickHandelMyLibrary = (navigation) => {
	navigation.navigate("MyLibrary");
};
const ClickHandelLogout = (navigation) => {
	navigation.navigate("LogIn");
};

const CommunityHeader = ({ navigation }) => {
	console.log(navigation);
	return (
		<HeaderContainer>
			<TouchableOpacity onPress={() => ClickHome(navigation)}>
				<TitleTypo>PDFCampus</TitleTypo>
			</TouchableOpacity>
			<ButtonContainer>
				<TitleButton
					typo="마이페이지"
					onPress={() => ClickHandelMyPage(navigation)}
				/>
				<TitleButton
					typo="나의 서재"
					onPress={() => ClickHandelMyLibrary(navigation)}
				/>
				<TitleButton
					typo="로그아웃"
					onPress={() => ClickHandelLogout(navigation)}
				/>
			</ButtonContainer>
		</HeaderContainer>
	);
};

export default CommunityHeader;
