import { useRef, useState } from "react";
import { Text, Alert } from "react-native";

import styled from "styled-components";

import CommunityButton from "../organisms/CommunityButton";

const TitleContainer = styled.View`
	width: 30%;
	height: 20%;
	display: flex;
	flex-direction: column;
	justify-content: flex-end;
	align-items: center;
`;

const Container = styled.View`
	width: 100%;
	height: 70%;
	display: flex;
	flex-direction: column;
	justify-content: space-evenly;
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

	background: #D9D9D9;
	border-radius: 100px;
`;

const UserInfoContainer = styled.View`
	width: 650px;
	height: 205px;

	display: flex;
	flex-direction:row;
	//justify-content: center;
	align-items: center;
	
	border: 2px solid #000000;
//	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	border-radius: 20px;
`;

const SubInfoContainer = styled.View`
	width: 650px;
	height: 205px;
	
	display: flex;
	flex-direction:row;
	justify-content: flex-start;
	align-items: flex-start;
`;

const UserPic = styled.View`
	width: 200px;
	height: 205px;
	justify-content: center;
	align-items : center;
`;

const UserIntro = styled.View`
	width: 450px;
	height: 205px;
	flex-direction:row;
	justify-content: space-around;
	align-items : center;
`;

const SubIntro = styled.View`
	width: 650px;
	height: 205px;
	justify-content: center;
	align-items : flex-start;
`;

const ButtonIntro = styled.View`
	width: 650px;
	height: 10px;
	justify-content: center;
`;

const SubOnClick = () => {
	Alert.alert("구독하러");
};

const UserInfo = {
	name:"이현정",
	userIntro:"안녕하세요 자기소개입니다",
	isSubscribed:true,
	startDate:"2023-01-01",
	subDate:"2023-05-01",
	subItem:"6개월",
	period:"160일"
}

const MyPage = () => {
	if(UserInfo.isSubscribed = true)
	{
		return (
			<>
				<TitleContainer>
					<PDFCampusTypo>마이페이지</PDFCampusTypo>
				</TitleContainer>
				<Container>
					<UserInfoContainer>
					<UserPic>
						<UserPicture></UserPicture>
					</UserPic>
					<UserIntro>
						<Typoone>{UserInfo.name}</Typoone>
						<Typotwo>{UserInfo.userIntro}</Typotwo>
						</UserIntro>
					</UserInfoContainer>
	
					<SubInfoContainer>
						<Typoone>구독정보</Typoone>
						<SubIntro>
						<Typotwo>가입일 : {UserInfo.startDate}</Typotwo>
						<Typotwo>구독일 : {UserInfo.subDate}</Typotwo>
						<Typotwo>상품 : {UserInfo.subItem}</Typotwo>
						<Typotwo>남은 기간 : {UserInfo.period}</Typotwo>
						</SubIntro>
					</SubInfoContainer>
				</Container>
			</>
				);

	}
	
	else 
	{
		return (
			<>
				<TitleContainer>
					<PDFCampusTypo>마이페이지</PDFCampusTypo>
				</TitleContainer>
				<Container>
					<UserInfoContainer>
					<UserPic>
						<UserPicture></UserPicture>
					</UserPic>
					<UserIntro>
						<Typoone>{UserInfo.name}</Typoone>
						<Typotwo>{UserInfo.userIntro}</Typotwo>
						</UserIntro>
					</UserInfoContainer>
	
					<SubInfoContainer>
						<Typoone>구독정보</Typoone>
						<SubIntro>
							<Typotwo>현재 구독 정보가 없습니다. {'\n'}서비스를 구독하고 더 많은 서비스를 이용해보세요!</Typotwo>
						</SubIntro>
					</SubInfoContainer>
					<ButtonIntro>
					<CommunityButton typo="구독 하러 가기" onPress={SubOnClick} />
					</ButtonIntro>
				</Container>
			</>
				);
	}
	
};

export default MyPage;