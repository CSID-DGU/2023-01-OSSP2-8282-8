import React from "react";
import styled from "styled-components";
import ImageBlock from "../molecules/ImageBlock";
import DateBlock from "../molecules/DateBlock";
import BookButtonBlock from "../molecules/BookButtonBlock";

const Container = styled.View`
	width: 100%;
	display: flex;
	align-items: center;
	flex-direction: row;
	margin-bottom: 30px;
`;

const UpperDetail = ({
	img,
	PublicationDate,
	ModifiedDate,
	isStored,
	truepress,
	falsepress,
}) => {
	return (
		<Container>
			<ImageBlock img={img} />
			<DateBlock
				PublicationDate={PublicationDate}
				ModifiedDate={ModifiedDate}
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
