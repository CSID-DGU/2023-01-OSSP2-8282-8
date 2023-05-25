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

const DateBlock = ({ info1, info2, isBook }) => {
	return (
		<Container>
			<DateContainer>
				<DateInfoTypo>{isBook ? "출판 년도" : "등록 날짜"}</DateInfoTypo>
				<DateInfoTypo>{info1}</DateInfoTypo>
			</DateContainer>
			{isBook ? (
				<DateContainer>
					<DateInfoTypo>최근 수정 날짜</DateInfoTypo>
					<DateInfoTypo>{info2}</DateInfoTypo>
				</DateContainer>
			) : (
				<DateContainer>
					<DateInfoTypo>최근 수정 날짜</DateInfoTypo>
					<DateInfoTypo>{info2}</DateInfoTypo>
				</DateContainer>
			)}
		</Container>
	);
};

export default DateBlock;
