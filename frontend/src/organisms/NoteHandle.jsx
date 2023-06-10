import styled from "styled-components";

import DateBlock from "../molecules/DateBlock";
import NoteHandleButton from "../molecules/NoteHandleButtons";

import isSaleImg from "../../assets/is_sale.png";
import notSaleImg from "../../assets/not_sale.png";

const InfoContainer = styled.View`
	display: flex;
	flex-direction: row;
	margin: 20px 0;
`;
const Divider = styled.View`
	width: 100%;
	height: 2px;
	background: #bebebe;
`;

const DateContainer = styled.View`
	display: flex;
	height: 130px;
	justify-content: center;
`;

const Img = styled.Image`
	width: 30%;
	height: 190px;
	resize: contain;
	box-sizing: border-box;
`;

const Block1 = styled.View`
	width: 20%;
	display: flex;
	align-items: center;
`;
const Block2 = styled.View`
	display: flex;
	width: 40%;
	display: flex;
`;
const IsSaledBlock = styled.View`
	display: flex;
	flex-direction: row;
	align-items: center;
	height: 20px;
	margin-top: 15px;
`;

const TitleTypo = styled.Text`
	font-size: 16px;
	justify-content: center;
	margin-top: 0px;
`;
const DateInfoTypo = styled.Text`
	font-size: 18px;
	color: black;
	font-weight: 600;
	white-space: pre-wrap;
	margin-top: 10px;
	margin-bottom: 10px;
`;

const IsSaledImg = styled.Image`
	width: 20px;
	height: 20px;
	margin-right: 5px;
	resize: contain;
	margin-top: 8px;
`;

const NoteHandle = ({
	id,
	img,
	bookTitle,
	isSaled,
	PublicationDate,
	ModifiedDate,
	onPress1,
	onPress2,
}) => {
	return (
		<>
			<InfoContainer>
				<Block1>
					<Img
						source={{ uri: img }}
						style={{ width: 140, height: 190, resizeMode: "contain" }}
					/>
					<TitleTypo>
						{bookTitle.length > 16 ? bookTitle.slice(0, 14) + "..." : bookTitle}
					</TitleTypo>
				</Block1>
				<Block2>
					<DateContainer>
						<DateInfoTypo>
							등록날짜{"\n"}
							{PublicationDate}
						</DateInfoTypo>
						<DateInfoTypo>
							등록날짜{"\n"}
							{ModifiedDate}
						</DateInfoTypo>
					</DateContainer>
					<IsSaledBlock>
						{isSaled ? (
							<IsSaledImg style={{ marginBottom: 7 }} source={isSaleImg} />
						) : (
							<IsSaledImg style={{ marginBottom: 7 }} source={notSaleImg} />
						)}
						<TitleTypo>판매여부</TitleTypo>
					</IsSaledBlock>
				</Block2>
				<NoteHandleButton
					isSaled={isSaled}
					onPress1={onPress1}
					onPress2={onPress2}
					id={id}
				/>
			</InfoContainer>
			<Divider />
		</>
	);
};

export default NoteHandle;
