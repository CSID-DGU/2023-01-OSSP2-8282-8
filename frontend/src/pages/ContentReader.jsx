import React, { useEffect, useState } from "react";
import Header from "../organisms/Header";
import CanvasComponent from "../organisms/CanvasComponent";
import styled from "styled-components";

import getBookContent from "../../api/getBookContent";

const Container = styled.View`
	display: flex;
	position: relative;
	z-index: 1;
`;

const ContentReader = ({ navigation, route }) => {
	const { type, contentId } = route.params;
	const [currentPage, setCurrentPage] = useState(0);
	const [pages, setPages] = useState([]);
	const prevOnClick = () => {
		setCurrentPage(currentPage > 1 ? currentPage - 2 : currentPage);
	};
	const nextOnClick = () => {
		setCurrentPage(
			currentPage < pages.length - 2 ? currentPage + 2 : currentPage
		);
	};
	const handlePages = (pages) => {
		setPages(pages);
	};
	useEffect(() => {
		if (type == "book") {
			getBookContent(contentId, handlePages);
		} else {
			// get note content
		}
	}, []);
	return (
		<Container>
			<Header navigation={navigation} />
			<CanvasComponent
				content={pages}
				prevOnClick={prevOnClick}
				nextOnClick={nextOnClick}
				currentPage={currentPage}
			/>
		</Container>
	);
};

export default ContentReader;
