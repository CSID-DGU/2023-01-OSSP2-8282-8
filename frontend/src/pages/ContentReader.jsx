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

const ContentReader = () => {
	const [pages, setPages] = useState([]);
	const handlePages = (pages) => {
		setPages(pages);
	};
	useEffect(() => {
		getBookContent(handlePages);
	}, []);
	return (
		<Container>
			<Header />
			<CanvasComponent />
		</Container>
	);
};

export default ContentReader;
