import { useEffect, useRef, useState } from "react";
import { Text, Alert } from "react-native";

import styled from "styled-components";

import CommunityButton from "../organisms/CommunityButton";
import ViewAllButton from "../organisms/ViewAllButton";
import Header from "../organisms/Header";
import ListContainer from "../organisms/ListContainer";

import getMyLib from "../../api/getMyLib";
import { useRecoilValue } from "recoil";
import { UserInfoState } from "../../state/UserInfoState";

const Container = styled.View`
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: column;
	align-items: center;
	box-sizing: border-box;
	padding: 40px 0 0 0;
`;

const ContentWrapper = styled.View`
	width: 100%;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
`;

const ButtonWrapper = styled.View`
	width: 210px;
	height: 45px;
	box-sizing: border-box;
	margin: 0 0 8px 30px;
`;

const ViewAllButtonWrapper = styled.View`
	box-sizing: border-box;
	margin: 150px 150px 0 0;
`;

const TitleContainer = styled.View`
	width: 80%;
	height: 100px;
	display: flex;
	flex-direction: row;
	align-items: center;
`;

const ListWrapper = styled.View`
	width: 65%;
	display: flex;
	flex-direction: row;
	box-sizing: border-box;
	margin: 0 0 0 30px;
`;

const PDFCampusTypo = styled.Text`
	font-weight: 600;
	font-size: 40px;
`;

const Line = styled.View`
	width: 80%;
	height: 2px;
	background: #bebebe;
	box-sizing: border-box;
	margin: 25px 0 0 0;
`;

const SubOnClick = () => {
	Alert.alert("필기관리창");
};

const ViewAllNotesOnClick = () => {
	Alert.alert("필기 전체보기");
};

const ViewAllPDFOnClick = () => {
	Alert.alert("도서 전체보기");
};

const LIST1Info = {
	name:"operating system"
}

const LIST2Info = {
	name:"operating system"
}

const MyLibrary = () => {
	const [books, setBooks] = useState([]);
	const [notes, setNotes] = useState([]);
	const [bookMore, setBookMore] = useState(false);
	const [noteMore, setNoteMore] = useState(false);
	const handleContents = (books, notes, bookMore, noteMore) => {
		setBooks(books);
		setNotes(notes);
		setBookMore(bookMore);
		setNoteMore(noteMore);
	};
	const userInfo = useRecoilValue(UserInfoState);
	useEffect(() => {
		getMyLib(userInfo.userId, handleContents);
	}, []);
	return (
		<>
			<Header />

			<Container>
				<TitleContainer>
					<PDFCampusTypo>내가 구매한 필기</PDFCampusTypo>
					<ButtonWrapper>
						<CommunityButton typo="나의 필기 관리" onPress={SubOnClick} />
					</ButtonWrapper>
				</TitleContainer>
				<ContentWrapper>
					<ListWrapper>
						<ListContainer products={books} type="book" />
					</ListWrapper>
					<ViewAllButtonWrapper>
						<ViewAllButton typo="필기 전체보기" onPress={SubOnClick} />
					</ViewAllButtonWrapper>
				</ContentWrapper>
				<Line />
				<TitleContainer>
					<PDFCampusTypo>나의 서재 리스트</PDFCampusTypo>
				</TitleContainer>
				<ContentWrapper>
					<ListWrapper>
						<ListContainer products={notes} type="note" />
					</ListWrapper>
					<ViewAllButtonWrapper>
						<ViewAllButton typo="도서 전체보기" onPress={SubOnClick} />
					</ViewAllButtonWrapper>
				</ContentWrapper>
			</Container>
		</>
	);
};

export default MyLibrary;
