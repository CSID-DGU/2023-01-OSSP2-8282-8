import { Text, View } from "react-native";
import styled from "styled-components";
import "react-native-gesture-handler";
import { createStackNavigator } from "@react-navigation/stack";
import { NavigationContainer } from "@react-navigation/native";

const Stack = createStackNavigator();

const Container = styled.View`
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
`;

export default function App() {
	return (
		<Container>
			<Text>hello</Text>
		</Container>
	);
}
