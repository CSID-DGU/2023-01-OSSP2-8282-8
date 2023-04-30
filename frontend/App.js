import "react-native-gesture-handler";
import { createStackNavigator } from "@react-navigation/stack";
import { NavigationContainer } from "@react-navigation/native";
import SignUp from "./src/pages/SignUp";

const Stack = createStackNavigator();

export default function App() {
	return (
		// <NavigationContainer>
		// 	<Stack.Navigator initialRouteName="SignUp">
		// 		<Stack.Screen name="SignUp" component={SignUp} />
		// 	</Stack.Navigator>
		// </NavigationContainer>
		<SignUp />
	);
}
