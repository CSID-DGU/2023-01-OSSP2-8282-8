import { useEffect, useRef, useState } from "react";
import { TouchableOpacity, Text, Alert, Image } from "react-native";

import styled from "styled-components";

import CommunityButton from "../organisms/CommunityButton";
import ViewAllButton from "../organisms/ViewAllButton";
import CompleteButton from "../organisms/CompleteButton";
import Search from "../organisms/Search";
import Header from "../organisms/Header";
import getSearch from "../../api/getSearch";
import { useRecoilValue } from "recoil";
import { UserInfoState } from "../../state/UserInfoState";
import ImageBlock from "../molecules/ImageBlock";

const Container = styled.View`
	width: 100%;
	height: 100%;
	display: flex;
	align-items: center;
`;

const SearchContainer = styled.View`
	width: 100%;
	height: 20%;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
`;

const Line = styled.View`
	width: 60%;
	height: 0px;

	border: 1px solid #bebebe;
`;

const ListContainer = styled.View`
	width: 100%;
	height: 80%;
	display: flex;
	justify-content: flex-first;
	align-items: center;
`;

const LIST = styled.View`
	width: 60%;
	height: 30%;
	flex-direction: row;
	align-items: flex-end;
	justify-content: space-between;
`;

const Picture = styled.View`
	box-sizing: border-box;
	width: 128px;
	height: 168px;

	background: #d9d9d9;
	border: 1.5px solid #bebebe;
`;

const Typoone = styled.Text`
	font-weight: 600;
	font-size: 20px;
	line-height: 35px;
`;

const Typotwo = styled.Text`
	font-weight: 500;
	font-size: 16px;
	line-height: 25px;
`;

const SubIntro = styled.View`
	width: 50%;
	height: 80%;
	justify-content: flex-start;
	align-items: flex-start;
	line-height: 19px;
`;

const ButtonIntro = styled.View`
	width: 20%;
	height: 30%;
	justify-content: flex-start;
	align-items: flex-start;
	line-height: 19px;
`;

const GotoLibarary = () => {
	Alert.alert("나의 서재에 담기 완료");
};

const BookResultList = (bookcontents) => {
	return (
		<ListContainer>
			{bookcontents.bookContents.map((book, index) => (
				<>
					<LIST key={index}>
						<Image
							source={{
								uri: `https://pdfampus.s3.ap-northeast-2.amazonaws.com/3.jpg`,
								//uri: {product.bookCover}
								// product.bookCover
							}}
							style={{ width: 130, height: 190 }}
						/>
						<SubIntro>
							<Typoone>
								[도서] {book.bookTitle} {book.publicationYear}판
							</Typoone>
							<Typotwo>저자 : {book.author}</Typotwo>
							<Typotwo>출판사 : {book.publisher}</Typotwo>
						</SubIntro>
						<ButtonIntro>
							<ViewAllButton typo="나의 서재 담기" onPress={GotoLibarary} />
						</ButtonIntro>
					</LIST>
					<Text>{"\n"}</Text>
					<Line />
				</>
			))}
		</ListContainer>
	);
};
const NoteResultList = (notecontents) => {
	return (
		<ListContainer>
			{notecontents.noteContents.map((note, index) => (
				<>
					<LIST key={index}>
						<Picture></Picture>
						<SubIntro>
							<Typoone>
								[필기] {note.bookInfo.bookTitle} {note.bookInfo.publicationYear}
								판
							</Typoone>
							<Typotwo>저자 : {note.booInfo.author}</Typotwo>
							<Typotwo>출판사 : {note.booInfo.publisher}</Typotwo>
							<Typotwo>작성자 : {note.author}</Typotwo>
						</SubIntro>
						<ButtonIntro>
							<ViewAllButton typo="나의 서재 담기" onPress={GotoLibarary} />
						</ButtonIntro>
					</LIST>
					<Text>{"\n"}</Text>
					<Line />
				</>
			))}
		</ListContainer>
	);
};

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

const SearchResult = ({ navigation, route }) => {
	const { type, keyword } = route.params;

	const [SearchContent, setSearchContent] = useState(""); //검색 키워드 저장
	const [contents, setContents] = useState([]);
	const [searchTypeText, setSearchTypeText] = useState(
		type == "book" ? "도서" : "필기"
	);

	const userId = useRecoilValue(UserInfoState).userId;

	const handleContents = (contentsList) => {
		setContents(contentsList);
	};
	const SearchClick = () => {
		//books 나 notes 로 지정하면 오류나서 book과 note로 대체
		navigation.navigate("SearchResult", {
			type: searchTypeText == "도서" ? "book" : "note",
			keyword: SearchContent,
		});
	};
	const HandleSearch = (text) => {
		setSearchContent(text);
	};
	const TypeSelect = () => {
		if (searchTypeText === "도서") {
			setSearchTypeText("필기");
		} else {
			setSearchTypeText("도서");
		}
	};

	useEffect(() => {
		getSearch(handleContents, type, keyword, userId);
	}, []);

	return (
		<>
			<Container>
				<Header />
				<SearchContainer>
					<SearchButton press={TypeSelect} type={searchTypeText} />
					<Search press={SearchClick} changeHandler={HandleSearch} />
				</SearchContainer>
				{type === "book" ? (
					<BookResultList bookContents={contents} />
				) : (
					<NoteResultList noteContents={contents} />
				)}
			</Container>
		</>
	);
};

export default SearchResult;
