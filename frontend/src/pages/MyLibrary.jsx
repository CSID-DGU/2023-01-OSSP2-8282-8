import { useRef, useState } from "react";
import { Text, Alert } from "react-native";

import styled from "styled-components";

import CommunityButton from "../organisms/CommunityButton";
import ViewAllButton from "../organisms/ViewAllButton";

const Container = styled.View`
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: row;
	justify-content: flex-first;
	align-items: center;
`;

const Container1 = styled.View`
	width: 80%;
	height: 100%;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
`;

const Container2 = styled.View`
	width: 15%;
	height: 80%;
	justify-content: flex-end;
`;

const ButtonIntro = styled.View`

	width: 25%;
	height: 100%;
	justify-content: center;
	align-items: center;
`;

const ButtonIntro2 = styled.View`
	width: 100%;
	height: 50%;
	justify-content: flex-end;
`;

const TitleContainer = styled.View`
	width:  80%;
	height: 100px;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
`;

const ListContainer = styled.View`
	width: 80%;
    height: 250px;
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	align-items: flex-first;
`;

const LIST1 = styled.View`
	box-sizing: border-box;
	width: 140px;
	height: 190px;
`;

const LIST1_Pic = styled.View`
	box-sizing: border-box;
	width: 140px;
	height: 190px;

	background: #FFFFFF;
	border: 1px solid #000000;
`;

const LIST1_Title = styled.View`
	box-sizing: border-box;
	width: 140px;
	height: 30px;

	justify-content: center;
	align-items: center;
`;

const LIST2 = styled.View`
	box-sizing: border-box;
	width: 140px;
	height: 190px;
`;

const LIST2_Pic = styled.View`
	box-sizing: border-box;
	width: 140px;
	height: 190px;

	background: #FFFFFF;
	border: 1px solid #000000;
`;

const LIST2_Title = styled.View`
	box-sizing: border-box;
	width: 140px;
	height: 30px;

	justify-content: center;
	align-items: center;
`;

const PDFCampusTypo = styled.Text`
	font-weight: 600;
	font-size: 40px;
`;

const Line = styled.View`	
	width: 80%;
	height: 0px;

	border: 1.5px solid #BEBEBE;
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

    return (
		<>
	<Container>
        <Container1>
            <TitleContainer>
			    <PDFCampusTypo>내가 구매한 필기</PDFCampusTypo>
				<ButtonIntro>
				<CommunityButton typo="나의 필기 관리" onPress={SubOnClick} />
				</ButtonIntro>
            </TitleContainer>
            <ListContainer>
				<LIST1>
					<LIST1_Pic></LIST1_Pic>
					<LIST1_Title><Text>{LIST1Info.name}</Text></LIST1_Title>
				</LIST1>
				<LIST1>
					<LIST1_Pic></LIST1_Pic>
					<LIST1_Title><Text>operating system2</Text></LIST1_Title>
				</LIST1>
				<LIST1></LIST1>
				<LIST1></LIST1>
				
			</ListContainer>
                <Line></Line>

            <TitleContainer>
                <PDFCampusTypo>나의 서재 리스트</PDFCampusTypo>
            </TitleContainer>
            <ListContainer>
				<LIST2>
					<LIST2_Pic></LIST2_Pic>
					<LIST2_Title><Text>operating system1</Text></LIST2_Title>
				</LIST2>
				<LIST2>
					<LIST2_Pic></LIST2_Pic>
					<LIST2_Title><Text>operating system2</Text></LIST2_Title>
				</LIST2>
				<LIST2>
					<LIST2_Pic></LIST2_Pic>
					<LIST2_Title><Text>operating system3</Text></LIST2_Title>
				</LIST2>
				<LIST2>
					<LIST2_Pic></LIST2_Pic>
					<LIST2_Title><Text>operating system4</Text></LIST2_Title>
				</LIST2>
			</ListContainer>
		</Container1>
		<Container2>
			<ButtonIntro2>
			<ViewAllButton typo="필기 전체보기" onPress={ViewAllNotesOnClick} />
			</ButtonIntro2>
			
			<ButtonIntro2>
			<ViewAllButton typo="도서 전체보기" onPress={ViewAllPDFOnClick} />
			</ButtonIntro2>
		</Container2>
	</Container>
		</>
	);
};

export default MyLibrary;