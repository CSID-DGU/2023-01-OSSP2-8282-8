import React from "react";
import styled from "styled-components";

const Container = styled.View`
	display: flex;
`;
const DateContainer = styled.View`
	height: 80px;
	display: flex;
	justify-content: space-between;
	box-sizing: border-box;
	padding: 0 0 20px 0;
`;
const DateInfoTypo = styled.Text`
	font-size: 20px;
	color: black;
	font-weight: 600;
`;

const DateBlock = ({ PublicationDate, ModifiedDate }) => {
	return (
		<Container>
			<DateContainer>
				<DateInfoTypo>등록 날짜</DateInfoTypo>
				<DateInfoTypo>{PublicationDate}</DateInfoTypo>
			</DateContainer>
			<DateContainer>
				<DateInfoTypo>최근 수정 날짜</DateInfoTypo>
				<DateInfoTypo>{ModifiedDate}</DateInfoTypo>
			</DateContainer>
		</Container>
	);
};

export default DateBlock;
