import React from "react";
import styled from "styled-components";
//import CommunityInput from "../molecules/CommunityInput";
import { TouchableOpacity, View, Image } from "react-native";
//import { TouchableOpacity, View, Image } from "react-native-web";

const Container = styled.View`
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 20px;
	margin-bottom: 30px;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
`;
const SearchWeapper = styled.View`
	width: 400px;
	height: 50px;
	border-radius: 11px;
	background: #d9d9d9;
	justify-content: space-around;
	align-items: center;
	flex-direction: row;
`;
const ButtonWrapper = styled.View`
	width: 60px;
	height: 38px;
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 11px;
	background: #848484;
`;

const Input = styled.TextInput`
	width: 80%;
	height: 38px;
	background: #fff;
	border-radius: 10px;
	margin: 5.5px 0;
	padding: 6px;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	font-weight: 400;
	font-size: 12px;
	color: #000;
`;

const ButtonTypo = styled.Text`
	font-size: 16px;
	color: #fff;
`;

const SearchInput = ({ typo, changeHandler, textInputRef }) => {
	return (
		<Input
			placeholder={typo}
			placeholderTextColor={"#cdcdcd"}
			onChangeText={changeHandler}
			ref={textInputRef}
		/>
	);
};

const SearchButton = ({ press }) => {
	return (
		<TouchableOpacity onPress={press}>
			<ButtonWrapper>
				<ButtonTypo>검색</ButtonTypo>
			</ButtonWrapper>
		</TouchableOpacity>
	);
};

const Search = ({ press, changeHandler }) => {
	return (
		<Container>
			<SearchWeapper>
				<SearchInput typo="검색" changeHandler={changeHandler} />
				<SearchButton press={press} />
			</SearchWeapper>
		</Container>
	);
};

export default Search;
