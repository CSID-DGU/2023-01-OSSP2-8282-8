import "react-native-gesture-handler";
import { RecoilRoot } from "recoil";
import { createStackNavigator } from "@react-navigation/stack";
import { NavigationContainer } from "@react-navigation/native";
import SignUp from "./src/pages/SignUp";
import LogIn from "./src/pages/Login";
import MainPage from "./src/pages/MainPage";
import MyPage from "./src/pages/MyPage";
import MyLibrary from "./src/pages/MyLibrary";
import BookDetail from "./src/pages/BookDetail";
import NoteDetail from "./src/pages/NoteDetail";
import MyNotes from "./src/pages/MyNotes";
import NoteHandle from "./src/organisms/NoteHandle";

const Stack = createStackNavigator();

export default function App() {
	return (
		// <NavigationContainer>
		// 	<Stack.Navigator initialRouteName="SignUp">
		// 		<Stack.Screen name="SignUp" component={SignUp} />
		// 	</Stack.Navigator>
		// </NavigationContainer>
		<RecoilRoot>
			{/* <LogIn /> */}
			{/* <MyPage /> */}
   		{/*	<MyLibrary />*/}
		  <MyNotes />
		</RecoilRoot>
	);
}
