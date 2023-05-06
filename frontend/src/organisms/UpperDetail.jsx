import React from "react";
import styled from "styled-components";
import ImageBlock from "../molecules/ImageBlcok";
import DateBlock from "../molecules/DateBlock";
import BookButtonBlock from "../molecules/BookButtonBlock";

const Container = styled.View`
	width: 100%;
	display: flex;
	align-items: center;
	flex-direction: row;
	margin-bottom: 30px;
`;

const UpperDetail = ({ contentInfo, truepress, falsepress }) => {
	const { bookCover, publicationDate, modifiedDate, isStored } = contentInfo;
	return (
		<Container>
			<ImageBlock img={bookCover} />
			<DateBlock
				PublicationDate={publicationDate}
				ModifiedDate={modifiedDate}
			/>
			<BookButtonBlock
				isStored={isStored}
				truepress={truepress}
				falsepress={falsepress}
			/>
		</Container>
	);
};

export default UpperDetail;
