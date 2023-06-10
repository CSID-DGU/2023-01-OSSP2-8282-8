import React, { useEffect, useState } from "react";
import Header from "../organisms/Header";
import CanvasComponent from "../organisms/CanvasComponent";
import styled from "styled-components";

import getBookContent from "../../api/getBookContent";
import getNoteContent from "../../api/getNoteContent";
import { useRecoilValue } from "recoil";
import { UserInfoState } from "../../state/UserInfoState";

const Container = styled.View`
	display: flex;
	position: relative;
	z-index: 1;
`;

const ContentReader = ({ navigation, route }) => {
	const { type, contentId } = route.params;
	const [currentPage, setCurrentPage] = useState(0);
	const [pages, setPages] = useState([]);

	const userId = useRecoilValue(UserInfoState).userId;

	const prevOnClick = () => {
		setCurrentPage(currentPage > 1 ? currentPage - 2 : currentPage);
	};
	const nextOnClick = () => {
		setCurrentPage(
			type == "note"
				? currentPage < pages.length * 2 - 2
					? currentPage + 2
					: currentPage
				: currentPage < pages.length - 2
				? currentPage + 2
				: currentPage
		);
	};
	const handlePages = (pages) => {
		setPages(pages);
	};
	useEffect(() => {
		if (type == "book") {
			getBookContent(contentId, handlePages);
		} else {
			getNoteContent(contentId, userId, handlePages);
		}
	}, []);
	return (
		<Container>
			<Header navigation={navigation} />
			<CanvasComponent
				isNote={type == "note"}
				content={pages}
				prevOnClick={prevOnClick}
				nextOnClick={nextOnClick}
				currentPage={currentPage}
				bookId={contentId}
			/>
		</Container>
	);
};

export default ContentReader;
