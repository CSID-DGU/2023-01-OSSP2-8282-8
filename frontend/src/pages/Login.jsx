import { useRef, useState } from "react";
import { Alert } from "react-native";

import styled from "styled-components";

import CommunityButton from "../organisms/CommunityButton";
import CommunityInputContainer from "../organisms/CommunityInputContainer";

import postLogin from "../../api/postLogin";
import { useSetRecoilState } from "recoil";
import { UserInfoState } from "../../state/UserInfoState";

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

const LoginForm = styled.View`
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

const LogIn = () => {
	const onChangeId = (e) => {
		setId(e);
	};
	const onChangePw = (e) => {
		setPw(e);
	};

	const idInputRef = useRef();
	const pwInputRef = useRef();

	const inputList = [
		{
			typo: "아이디를 입력하세요",
			changeHandler: onChangeId,
			textInputRef: idInputRef,
		},

		{
			typo: "비밀번호를 입력하세요",
			changeHandler: onChangePw,
			textInputRef: pwInputRef,
		},
	];

	const [id, setId] = useState("");
	const [pw, setPw] = useState("");

	const setUserInfo = useSetRecoilState(UserInfoState);
	const handleUserInfo = (info) => {
		setUserInfo(info);
	};

	const LogInClick = () => {
		postLogin(
			{
				id: id,
				password: pw,
			},
			handleUserInfo
		);
		setId("");
		setPw("");
		idInputRef.current.clear();
		pwInputRef.current.clear();
	};

	return (
		<Container>
			<PDFCampusTypoWrapper>
				<PDFCampusTypo>PDFCampus</PDFCampusTypo>
			</PDFCampusTypoWrapper>
			<LoginForm>
				<CommunityInputContainer inputList={inputList} />
				<CommunityButton typo="로그인" onPress={LogInClick} />
			</LoginForm>
		</Container>
	);
};

export default LogIn;
