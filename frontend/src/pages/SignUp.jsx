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

const SignUpForm = styled.View`
	width: 303px;
	height: 356px;
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

const SignUp = () => {
	const onChangeId = (e) => {
		setId(e);
	};
	const onChangeNickname = (e) => {
		setNickname(e);
	};
	const onChangePw = (e) => {
		setPw(e);
	};
	const onChangePwCheck = (e) => {
		setPwCheck(e);
	};

	const idInputRef = useRef();
	const nicknameInputRef = useRef();
	const pwInputRef = useRef();
	const pwCheckInputRef = useRef();

	const inputList = [
		{
			typo: "아이디를 입력하세요",
			changeHandler: onChangeId,
			textInputRef: idInputRef,
		},
		{
			typo: "닉네임 입력하세요",
			changeHandler: onChangeNickname,
			textInputRef: nicknameInputRef,
		},
		{
			typo: "비밀번호를 입력하세요",
			changeHandler: onChangePw,
			textInputRef: pwInputRef,
		},
		{
			typo: "비밀번호 확인",
			changeHandler: onChangePwCheck,
			textInputRef: pwCheckInputRef,
		},
	];

	const [id, setId] = useState("");
	const [nickname, setNickname] = useState("");
	const [pw, setPw] = useState("");
	const [pwCheck, setPwCheck] = useState("");

	const signUpOnClick = () => {
		setId("");
		setNickname("");
		setPw("");
		setPwCheck("");
		idInputRef.current.clear();
		nicknameInputRef.current.clear();
		pwInputRef.current.clear();
		pwCheckInputRef.current.clear();
		if (pw !== pwCheck)
			Alert.alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
	};

	return (
		<Container>
			<PDFCampusTypoWrapper>
				<PDFCampusTypo>PDFCampus</PDFCampusTypo>
			</PDFCampusTypoWrapper>
			<SignUpForm>
				<CommunityInputContainer inputList={inputList} />
				<CommunityButton typo="회원가입" onPress={signUpOnClick} />
			</SignUpForm>
		</Container>
	);
};

export default SignUp;
