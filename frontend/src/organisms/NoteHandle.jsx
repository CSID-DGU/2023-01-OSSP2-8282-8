import styled from "styled-components";

import DateBlock from "../molecules/DateBlock";
import NoteHandleButton from "../molecules/NoteHandleButtons";

const Container = styled.View`
	display: flex;
	height: 200px;
	margin-top: 20px;
`;
const InfoContainer = styled.View`
	display: flex;
	flex-direction: row;
	margin-bottom: 20px;
`;
const Divider = styled.View`
	width: 100%;
	height: 2px;
	background: #bebebe;
	box-sizing: border-box;
	margin-bottom: 17px;
`;

const DateContainer = styled.View`
	display: flex;
	height: 80%;
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
	height: 20%;
	margin-top: 15px;
`;

const TitleTypo = styled.Text`
	font-size: 20px;
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
		<Container>
			<InfoContainer>
				<Block1>
					<Img
						source={{ uri: img }}
						style={{ width: 140, height: 190, resizeMode: "contain" }}
					/>
					<TitleTypo>{bookTitle}</TitleTypo>
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
							<IsSaledImg
								source={{
									uri: "https://s3-alpha-sig.figma.com/img/890a/f97d/689d9b36abf0a092a31182eb5ff37467?Expires=1684713600&Signature=nXqQScrt~Gtc-aghpuuAfGGCBWdyGsCgFqVSef1gWRPpe-zNOxapKJCSIn5dK9K0uxKx9y8lAnKVeSvjnnp~bfXADJQ~6o9CnRN2yEnpXm7Il48at~S10cql1geRcj~KjYvDeZI1OuammZN18GTaTPauM0lY29CLTk-T8q9lOMEr8RhoEORumK2yga56moNXcgxlKuS~ZIH2p89Cj63n0OvYrtZUuNHMf5PBRCvn9nrewfCqJdh4g-3mED07ASJsQ9ZVMce12nlv3BTJIjwTKXjsp0L6MU5mkTyb9cEG77udOCUC4NspIWpMimrGp1~G~BqeOeu~IlFDdqsNa0Bfww__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
								}}
							/>
						) : (
							<IsSaledImg
								source={{
									uri: "https://s3-alpha-sig.figma.com/img/af6e/4a37/d500170a48988def6a48014c777d6b30?Expires=1684713600&Signature=VfPzqtQhfEPL9cMVKQgccNeucd7EmhEkSq~meo~RP8R~IxDy2eRS-7OZ3KTVehwvE3xAZf8usc6xcWAAtcnnYqyUrpPs6NOM-lHBpHtfwjZo8AY5Cv5Kp6SJz-HpEgBAviXMZhfHw0HZ5jgt69NdzBt2VLWOLSnmm0avi7PYddBtawswzQkoW8hTW2IRAu2PI4~UHfvXTqEziCiyAXOel6sDYpHYRQyqeIeXayjyTWW7DSs~Eotz1od3jcNfUg6vdVxV~73NyxH5ll0rkmJ1B0GbZACqIOyFQW0N7mmEfy0HZpwXNOq0xPIj89G9g~PNh~leWrXUa-xdgaI2QiBgQA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
								}}
							/>
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
		</Container>
	);
};

export default NoteHandle;
