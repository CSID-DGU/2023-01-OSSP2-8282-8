import { useRef, useState } from "react";
import { Alert } from "react-native";

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

const PDFCampusTypoWrapper = styled.View`
	width: 303px;
	height: 55px;
	display: flex;
	justify-content: center;
	align-items: center;
	box-sizing: border-box;
	margin: 15px 0;
`;
const PDFCampusTypo = styled.Text`
	font-weight: 600;
	font-size: 40px;
`;

const MyPageForm = styled.View`
	width: 303px;
	height: 250px;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	align-items: center;
	background: #efefed;
	box-sizing: border-box;
	padding: 23px;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	border-radius: 15px;
`;

const Mypage = () => {
	const isSubscribed = false;

	return (
		<Container>
			<PDFCampusTypoWrapper>
				<PDFCampusTypo>마이페이지</PDFCampusTypo>
			</PDFCampusTypoWrapper>
        {
            isSubscribed ? (
                <Switch>
                    <Text>구독 정보</Text>
                </Switch>
            ) : (
                <Switch>
                    <Text>현재 구독 정보가 없습니다.</Text>
                    <CommunityButton typo="구독하러가기" onPress={LogInClick} />
                </Switch>
            )}
        </Container>
	);
};

export default MyPage;