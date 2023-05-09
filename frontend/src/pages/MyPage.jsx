import { useEffect, useRef, useState } from "react";
import { Text, Alert } from "react-native";

import styled from "styled-components";

import CommunityButton from "../organisms/CommunityButton";
import Header from "../organisms/Header";

const Container = styled.View`
	display: flex;
	box-sizing: border-box;
	margin: 0 auto;
`;

const MyPageTypo = styled.Text`
	box-sizing: border-box;
	margin: 60px 0 0 0;
	font-weight: 600;
	font-size: 40px;
`;

const SubTitleTypo = styled.Text`
	font-weight: 600;
	font-size: 35px;
`;

const SelfIntroWrapper = styled.View`
	box-sizing: border-box;
	margin: 0 25px;
`;

const SelfIntroTypo = styled.Text`
	font-size: 15px;
	color: #555;
`;

const ContentTypo = styled.Text`
	font-weight: 300;
	font-size: 25px;
`;

const UserPicture = styled.View`
	width: 150px;
	height: 150px;
	display: flex;

	background: #d9d9d9;
	border-radius: 100px;
`;

const UserInfoContainer = styled.View`
	width: 650px;
	height: 205px;
	display: flex;
	flex-direction: row;
	align-items: center;
	background: #fff;
	border: 2px solid #000000;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	border-radius: 20px;
	box-sizing: border-box;
	margin: 42px 0;
`;

const SubInfoContainer = styled.View`
	width: 650px;
	height: 205px;
	display: flex;
	justify-content: flex-start;
	align-items: flex-start;
`;

const UserPic = styled.View`
	width: 200px;
	height: 205px;
	justify-content: center;
	align-items: center;
`;

const UserIntro = styled.View`
	height: 205px;
	flex-direction: row;
	justify-content: space-around;
	align-items: center;
`;

const SubIntro = styled.View`
	width: 650px;
	height: 205px;
	box-sizing: border-box;
	margin: 28px 0;
`;

const ButtonIntro = styled.View`
	width: 650px;
	height: 10px;
	justify-content: center;
`;

const SubOnClick = () => {
	Alert.alert("구독하러");
};

const MyPage = () => {
	const [subscribed, setSubscribed] = useState(false);
	const [subscribeInfo, setSubscribeInfo] = useState({
		joinedDate: "2022. 12. 13",
		subscribedDate: "2022. 12. 25",
		productName: "PDF 구독 (6개월)",
		remainDate: "165일",
	});

	return (
		<>
			<Header />
			<Container>
				<MyPageTypo>마이페이지</MyPageTypo>
				<UserInfoContainer>
					<UserPic>
						<UserPicture></UserPicture>
					</UserPic>
					<UserIntro>
						<SubTitleTypo>이름</SubTitleTypo>
						<SelfIntroWrapper>
							<SelfIntroTypo>안녕하세요 자기소개...</SelfIntroTypo>
						</SelfIntroWrapper>
					</UserIntro>
				</UserInfoContainer>

				<SubInfoContainer>
					<SubTitleTypo>구독정보</SubTitleTypo>

					{subscribed ? (
						<SubIntro>
							<ContentTypo>가입일: {subscribeInfo.joinedDate}</ContentTypo>
							<ContentTypo>구독일: {subscribeInfo.subscribedDate}</ContentTypo>
							<ContentTypo>상품: {subscribeInfo.productName}</ContentTypo>
							<ContentTypo>남은 기간: {subscribeInfo.remainDate}</ContentTypo>
						</SubIntro>
					) : (
						<SubIntro>
							<ContentTypo>
								현재 구독 정보가 없습니다. {"\n"}서비스를 구독하고 더 많은
								서비스를 이용해보세요!
							</ContentTypo>
						</SubIntro>
					)}
				</SubInfoContainer>
				{subscribed ? null : (
					<ButtonIntro>
						<CommunityButton typo="구독 하러 가기" onPress={SubOnClick} />
					</ButtonIntro>
				)}
			</Container>
		</>
	);
};

export default MyPage;
