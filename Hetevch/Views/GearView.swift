//
//  GearView.swift
//  Hetevch
//
//  Created by Bazarsad Uurdmandakh on 2023.04.07.
//

import SwiftUI

struct GearView: View {
    
    @State private var date = Date.now
    @State var chosenDate = Date.now
    
    var body: some View {
        VStack(spacing: 20) {
            HStack(alignment: .bottom, spacing: 8) {
                VStack(alignment: .leading, spacing: 8) {
                    Text("Миний зорилтот төлөвлөгөө")
                        .font(.custom("Inter-Regular", size: 12))
                        .foregroundColor(Color("LightGray"))
                    
                    Text("₮ 380,000.00")
                        .font(.custom("Inter-Regular_Bold", size: 24))
                        .foregroundColor(Color.white)
                }
                
                Spacer()
                
                Image("illustration_1")
            }
            .padding()
            .background(
                RoundedRectangle(cornerRadius: 8)
                    .foregroundColor(Color.black)
            )
            
            
            VStack(spacing: 8) {
                HStack() {
                    VStack(alignment: .leading) {
                        Text("Төсөв")
                            .font(.custom("Inter-Regular", size: 12))
                            .foregroundColor(Color("LightGray"))
                        
                        Text("₮ 380,000.00")
                            .font(.custom("Inter-Regular_Medium", size: 18))
                            .foregroundColor(Color.black)
                    }
                    Spacer()
                }
                .padding()
                .background(
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(Color("LightSmoke"), lineWidth: 1)
                )
                
                //Dropdown
                
                HStack() {
                    VStack(alignment: .leading) {
                        Text("Хугацаа")
                            .font(.custom("Inter-Regular", size: 12))
                            .foregroundColor(Color("LightGray"))
                        Menu {
                            ScrollView(.vertical) {
                                ForEach(0..<40, id: \.self) { index in
                                    let date = Calendar.current.date(byAdding: .day, value: index, to: Date())!
                                    Text(getFormattedDate(date:date) + " - " + getFormattedMonth(date: date) + "    |   \(index + 1) өдөр")
                                        .font(.custom("Inter-Regular_Bold", size:1))
                                        .foregroundColor(Color.black)
                                    
                                    
                                }
                            }
                            
                        } label: {
                            Text("4 сарын 9 хүртэл...")
                                .font(.custom("Inter-Regular_Medium", size: 18))
                        }
                        
                    }
                    Spacer()
                }
                .padding()
                .background(
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(Color("LightSmoke"), lineWidth: 1)
                )
                
                HStack() {
                    VStack(alignment: .leading) {
                        Text("Өдөрт төсөв")
                            .font(.custom("Inter-Regular", size: 12))
                            .foregroundColor(Color("LightGray"))
                        
                        Text("₮ 20,000.00")
                            .font(.custom("Inter-Regular_Medium", size: 18))
                            .foregroundColor(Color.black)
                    }
                    Spacer()
                }
                .padding()
                .background(
                    RoundedRectangle(cornerRadius: 8)
                        .foregroundColor(Color("Gray"))
                    )
                
            }
            
            Button(action: {
                
            }) {
                HStack {
                    Spacer()
                    Text("Хадгалах")
                        .font(.custom("Inter-Regular_Bold", size: 18))
                        .foregroundColor(Color.white)
                    Spacer()
                }
                .padding()
                .background(
                    RoundedRectangle(cornerRadius: 6)
                        .foregroundColor(Color.black)
                )
            }
            
            Spacer()
        }
        .padding()
    }
    
    private func getFormattedDate(date: Date) -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "dd"
        return dateFormatter.string(from: date)
    }
    
    private func getFormattedMonth(date: Date) -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "MMMM"
        return dateFormatter.string(from: date)
    }
}

struct GearView_Previews: PreviewProvider {
    static var previews: some View {
        GearView()
    }
}
