import SwiftUI
import shared
import Firebase

@main
struct iOSApp: App {
    init() {
        FirebaseApp.configure()
        KoinKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
