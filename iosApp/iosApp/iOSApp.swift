import SwiftUI
import shared
import Firebase

@main
struct iOSApp: App {
    init() {
        FirebaseApp.configure()
        Koin_iosKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
