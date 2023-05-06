import React from "react";
import styled from "styled-components";
import { useState } from "react";
import { TouchableOpacity, Image, Alert } from "react-native";

import CommunityHeader from "../organisms/Header";
import Search from "../organisms/Search";
import ListContainer from "../organisms/ListContainer";

const Container = styled.View`
	width: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
`;
const ListWrapper = styled.View`
	width: 80%;
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	align-items: center;
	margin-bottom: 30px;
`;
const ListTitleWrapper = styled.View`
	width: 200px;
	height: 60px;
	display: flex;
	justify-content: center;
	align-items: center;
	background: #d9d9d9;
	border-radius: 10px;
	margin: 0 auto 20px 150px;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
`;
const TitleTypo = styled.Text`
	font-size: 20px;
	color: black;
	font-weight: 900;
`;

const MainButtonLeft = ({ press }) => {
	return (
		<TouchableOpacity style={{ overflow: "hidden" }} onPress={press}>
			<Image
				source={{
					uri: "https://thumb.ac-illust.com/06/062b476579a41daea439432ac779b391_t.jpeg",
				}}
				style={{
					transform: [{ scaleX: -1 }],
					width: 30,
					height: 60,
					resizeMode: "contain",
				}}
			/>
		</TouchableOpacity>
	);
};

const MainButtonRight = ({ press }) => {
	return (
		<TouchableOpacity style={{ overflow: "hidden" }} onPress={press}>
			<Image
				source={{
					uri: "https://thumb.ac-illust.com/06/062b476579a41daea439432ac779b391_t.jpeg",
				}}
				style={{ width: 30, height: 60, resizeMode: "contain" }}
			/>
		</TouchableOpacity>
	);
};

const ListTitle = ({ typo }) => {
	return (
		<ListTitleWrapper>
			<TitleTypo>{typo}</TitleTypo>
		</ListTitleWrapper>
	);
};

const books = [
	{
		id: 1,
		name: "운영체제1",
		image: "https://image.yes24.com/goods/89496122/XL",
	},
	{
		id: 2,
		name: "운영체제2",
		image: "https://image.yes24.com/goods/89496122/XL",
	},
	{
		id: 3,
		name: "운영체제3",
		image: "https://image.yes24.com/goods/89496122/XL",
	},
	{
		id: 4,
		name: "운영체제4",
		image: "https://image.yes24.com/goods/89496122/XL",
	},
	{
		id: 5,
		name: "운영체제5",
		image: "https://image.yes24.com/goods/89496122/XL",
	},
	{
		id: 6,
		name: "운영체제6",
		image: "https://image.yes24.com/goods/89496122/XL",
	},
	{
		id: 7,
		name: "운영체제7",
		image: "https://image.yes24.com/goods/89496122/XL",
	},
	{
		id: 8,
		name: "운영체제8",
		image: "https://image.yes24.com/goods/89496122/XL",
	},
	{
		id: 9,
		name: "운영체제9",
		image: "https://image.yes24.com/goods/89496122/XL",
	},
	{
		id: 10,
		name: "운영체제10",
		image: "https://image.yes24.com/goods/89496122/XL",
	},
];

const notes = [
	{
		id: 1,
		name: "필기자료1",
		image:
			"https://simage.mujikorea.net/goods/31/11/79/07/4550002435097_N_N_400.jpg",
	},
	{
		id: 2,
		name: "필기자료2",
		image:
			"https://simage.mujikorea.net/goods/31/11/79/07/4550002435097_N_N_400.jpg",
	},
	{
		id: 3,
		name: "필기자료3",
		image:
			"https://simage.mujikorea.net/goods/31/11/79/07/4550002435097_N_N_400.jpg",
	},
	{
		id: 4,
		name: "필기자료4",
		image:
			"https://simage.mujikorea.net/goods/31/11/79/07/4550002435097_N_N_400.jpg",
	},
	{
		id: 5,
		name: "필기자료5",
		image:
			"https://simage.mujikorea.net/goods/31/11/79/07/4550002435097_N_N_400.jpg",
	},
	{
		id: 6,
		name: "필기자료6",
		image:
			"https://simage.mujikorea.net/goods/31/11/79/07/4550002435097_N_N_400.jpg",
	},
	{
		id: 7,
		name: "필기자료7",
		image:
			"https://simage.mujikorea.net/goods/31/11/79/07/4550002435097_N_N_400.jpg",
	},
	{
		id: 8,
		name: "필기자료8",
		image:
			"https://simage.mujikorea.net/goods/31/11/79/07/4550002435097_N_N_400.jpg",
	},
	{
		id: 9,
		name: "필기자료9",
		image:
			"https://simage.mujikorea.net/goods/31/11/79/07/4550002435097_N_N_400.jpg",
	},
	{
		id: 10,
		name: "필기자료10",
		image:
			"https://simage.mujikorea.net/goods/31/11/79/07/4550002435097_N_N_400.jpg",
	},
];

const MainPage = () => {
	const [pnum1, setPnum1] = useState(0);
	const [pnum2, setPnum2] = useState(5);
	const [pnum3, setPnum3] = useState(0);
	const [pnum4, setPnum4] = useState(5);
	const [SearchContent, setSearchContent] = useState(""); //검색 키워드 저장
	function handleClickBookNext() {
		if (pnum1 === 0) {
			setPnum1(5);
			setPnum2(10);
		}
	}
	function handleClickBookPrior() {
		if (pnum1 === 5) {
			setPnum1(0);
			setPnum2(5);
		}
	}
	function handleClickNoteNext() {
		if (pnum3 === 0) {
			setPnum3(5);
			setPnum4(10);
		}
	}
	function handleClickNotePrior() {
		if (pnum3 === 5) {
			setPnum3(0);
			setPnum4(5);
		}
	}
	SearchClick = () => {
		Alert.alert(SearchContent);
	};
	HandleSearch = (text) => {
		setSearchContent("검색: " + text);
	};
	return (
		<Container>
			<CommunityHeader />
			<Search press={SearchClick} changeHandler={HandleSearch} />
			<ListTitle typo="신규 도서 컨텐츠" />
			<ListWrapper>
				<MainButtonLeft press={handleClickBookPrior} />
				<ListContainer
					products={books.slice(pnum1, pnum2)}
					style={{ flex: 1 }}
				/>
				<MainButtonRight press={handleClickBookNext} />
			</ListWrapper>
			<ListTitle typo="신규 필기 컨텐츠" />
			<ListWrapper>
				<MainButtonLeft press={handleClickNotePrior} />
				<ListContainer
					products={notes.slice(pnum3, pnum4)}
					style={{ flex: 1 }}
				/>
				<MainButtonRight press={handleClickNoteNext} />
			</ListWrapper>
		</Container>
	);
};

export default MainPage;
