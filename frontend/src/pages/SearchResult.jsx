import { useRef, useState } from "react";
import { TouchableOpacity, Text, Alert } from "react-native";

import styled from "styled-components";

import CommunityButton from "../organisms/CommunityButton";
import ViewAllButton from "../organisms/ViewAllButton";
import CompleteButton from "../organisms/CompleteButton";
import Search from "../organisms/Search";

const Container = styled.View`
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: cloumn;
	justify-content: flex-first;
	align-items: center;
`;

const SearchContainer = styled.View`
	width: 100%;
	height: 20%;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: flex-end;
`;

const Line = styled.View`	
	width: 60%;
	height: 0px;

	border: 1px solid #BEBEBE;
`;

const ListContainer = styled.View`
	width: 100%;
	height: 80%;
	display: flex;
	flex-direction: cloumn;
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

	background: #D9D9D9;
	border: 1.5px solid #BEBEBE;
`;

const Typoone = styled.Text`
	font-weight: 600;
	font-size: 20px;
	line-height : 35px
`;

const Typotwo = styled.Text`
	font-weight: 500;
	font-size: 16px;
	line-height : 25px
`;

const SubIntro = styled.View`
	width: 50%;
	height: 80%;
	justify-content: flex-start;
	align-items : flex-start;
	line-height : 19px
`;

const ButtonIntro = styled.View`
	width: 20%;
	height: 30%;
	justify-content: flex-start;
	align-items : flex-start;
	line-height : 19px
`;

const GotoLibarary = () => {
	Alert.alert("나의 서재에 담기 완료");

};

const BookInfo = {
	code:"국내도서",
	bookTitle:"운영체제",
	author:"JeongJunHo",
	publisher:"퍼스트북",
	publicationYear:1,
	bookCover:"",
	isStored:true
}

const NoteInfo = {
	code:"필기",
	writer:"이현정",
	createdAt:"2023-01-01",
	modifiedAt:"2023-05-01",
	price:"2000",
	isBought:true,
	NoteCover:"",
	BookInfo:{
		bookTitle:"운영체제",
		author:"JeongJunHo",
		publisher:"퍼스트북",
		publicationYear:1,
	}
}

const SearchResult = () => {

	const [pnum1,setPnum1]=useState(0);
	const [pnum2,setPnum2]=useState(5);
	const [pnum3,setPnum3]=useState(0);
	const [pnum4,setPnum4]=useState(5);
	const [SearchContent,setSearchContent]=useState("");//검색 키워드 저장

	SearchClick = () => {
		Alert.alert(SearchContent);
	  }
	  HandleSearch = (text) =>{
		setSearchContent("검색: "+text);
	  }

	return (
			<>
				<Container>
					<SearchContainer>
						<Search press={SearchClick} changeHandler={HandleSearch}/>
					</SearchContainer>

					<ListContainer>
						<LIST>
						<Picture></Picture>
						<SubIntro>
							<Typoone>[{BookInfo.code}] {BookInfo.bookTitle} {BookInfo.publicationYear}판</Typoone>
							<Typotwo>저자 : {BookInfo.author}</Typotwo>
							<Typotwo>출판사 : {BookInfo.publisher}</Typotwo>
						</SubIntro>
						<ButtonIntro>
							<ViewAllButton typo="나의 서재 담기" onPress={GotoLibarary} />
						</ButtonIntro>
						</LIST>

						<Text>{"\n"}</Text>
						<Line></Line>

						<LIST>
						<Picture></Picture>
						<SubIntro>
							<Typoone>[{NoteInfo.code}] {NoteInfo.BookInfo.bookTitle} {NoteInfo.BookInfo.publicationYear}판</Typoone>
							<Typotwo>저자 : {NoteInfo.BookInfo.author}</Typotwo>
							<Typotwo>출판사 : {NoteInfo.BookInfo.publisher}</Typotwo>
							<Typotwo>작성자 : {NoteInfo.writer}</Typotwo>
						</SubIntro>
						<ButtonIntro>
							<ViewAllButton typo="나의 서재 담기" onPress={GotoLibarary} />
						</ButtonIntro>
						</LIST>

						<Text>{"\n"}</Text>
						<Line></Line>

						<LIST>
						<Picture></Picture>
						<SubIntro>
							<Typoone>[{NoteInfo.code}] {NoteInfo.BookInfo.bookTitle} {NoteInfo.BookInfo.publicationYear}판</Typoone>
							<Typotwo>저자 : {NoteInfo.BookInfo.author}</Typotwo>
							<Typotwo>출판사 : {NoteInfo.BookInfo.publisher}</Typotwo>
							<Typotwo>작성자 : {NoteInfo.writer}</Typotwo>
						</SubIntro>
						<ButtonIntro>
							<ViewAllButton typo="나의 서재 담기" onPress={GotoLibarary} />
						</ButtonIntro>
						</LIST>


					</ListContainer>
				</Container>
			</>
	);
	
};

export default SearchResult;