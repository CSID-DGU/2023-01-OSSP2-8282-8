import React from "react";
import styled from "styled-components";
import { useState, useEffect } from "react";
import { TouchableOpacity, Image, Alert } from "react-native";

import CommunityHeader from "../organisms/Header";
import Search from "../organisms/Search";
import ListContainer from "../organisms/ListContainer";
import getMain from "../../api/getMain";

const Container = styled.View`
	width: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	overflow-x: scroll;
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

const SearchContainer = styled.View`
	width: 100%;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
`;
const ButtonWrapper = styled.View`
	width: 60px;
	height: 38px;
	border-radius: 11px;
	background: #848484;
	align-items: center;
	justify-content: center;
`;
const ButtonTypo = styled.Text`
	font-size: 20px;
	color: white;
`;

const SearchButton = ({ press, type }) => {
	return (
		<TouchableOpacity onPress={press}>
			<ButtonWrapper>
				<ButtonTypo>{type}</ButtonTypo>
			</ButtonWrapper>
		</TouchableOpacity>
	);
};

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

const MainPage = ({ navigation }) => {
	const [books, setBooks] = useState([]);
	const [notes, setNotes] = useState([]);
	const [pnum1, setPnum1] = useState(0);
	const [pnum2, setPnum2] = useState(5);
	const [pnum3, setPnum3] = useState(0);
	const [pnum4, setPnum4] = useState(5);
	const [SearchContent, setSearchContent] = useState(""); //검색 키워드 저장
	const [searchTypeText, setSearchTypeText] = useState("도서");
	const handleContents = (newBooks, newNotes) => {
		setBooks(newBooks);
		setNotes(newNotes);
	};
	useEffect(() => {
		getMain(handleContents);
	}, []);
	const SearchClick = () => {
		navigation.navigate("SearchResult", {
			type: searchTypeText == "도서" ? "book" : "note",
			keyword: SearchContent,
		});
	};
	const TypeSelect = () => {
		if (searchTypeText == "도서") {
			setSearchTypeText("필기");
		} else {
			setSearchTypeText("도서");
		}
	};

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
	HandleSearch = (text) => {
		setSearchContent(text);
	};

	return (
		<Container>
			<CommunityHeader navigation={navigation} />
			<SearchContainer>
				<SearchButton press={TypeSelect} type={searchTypeText} />
				<Search press={SearchClick} changeHandler={HandleSearch} />
			</SearchContainer>
			<ListTitle typo="신규 도서 컨텐츠" />
			<ListWrapper>
				<MainButtonLeft press={handleClickBookPrior} />
				<ListContainer
					navigation={navigation}
					products={books.slice(pnum1, pnum2)}
					type="book"
					style={{ flex: 1 }}
				/>
				<MainButtonRight press={handleClickBookNext} />
			</ListWrapper>
			<ListTitle typo="신규 필기 컨텐츠" />
			<ListWrapper>
				<MainButtonLeft press={handleClickNotePrior} />
				<ListContainer
					navigation={navigation}
					products={notes.slice(pnum3, pnum4)}
					type="note"
					style={{ flex: 1 }}
				/>
				<MainButtonRight press={handleClickNoteNext} />
			</ListWrapper>
		</Container>
	);
};

export default MainPage;
