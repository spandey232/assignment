package com.assignment.demo;

import com.assignment.pojo.UserData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.UUID;

import com.opencsv.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("User Details CSV Program:");

		//CSV will be created on below filepath.
		String filepath="D:/DemoApp/assignment/users.csv";
		String sortedFilepath="D:/DemoApp/assignment/users-sorted.csv";
		DemoApplication demoObject=new DemoApplication();

		//to create user details entry in csv
		demoObject.createUserDataCSV(filepath);

		//to  sort the user details entry and create a new sorted data csv file
		demoObject.updateCSVInSortedOrderBasedOnUserName(filepath,sortedFilepath);

		//to fetch the userDetails based on given userName
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the userName to search userDetails in CSV:");
		String inputUserName=sc.next();
		demoObject.fetchAndPrintUserDetailsForGivenUserName(inputUserName,filepath);

	}

	public void createUserDataCSV(String filepath){
		File file = new File(filepath);
		try {
			FileWriter outputFile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputFile);
			String[] header = { "ID", "FirstName", "LastName","UserName","Email","Avatar","Gender","DOB","Address" };
			writer.writeNext(header);
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the number of random user entry in csv file:");
			int count=sc.nextInt();
			for(int i=0;i<count;i++){
				String[] data1 = {UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(),UUID.randomUUID().toString(),UUID.randomUUID().toString(),UUID.randomUUID().toString(),UUID.randomUUID().toString(),UUID.randomUUID().toString() ,UUID.randomUUID().toString()};
				writer.writeNext(data1);
				Thread.sleep(1500);
			}
			System.out.println("All "+count+ " random user entry has been done in CSV at filepath:"+filepath);
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateCSVInSortedOrderBasedOnUserName(String filePath,String sortedFilePath) throws IOException {
		ArrayList<UserData> csvData=new ArrayList<>();
		BufferedReader br=new BufferedReader(new FileReader(filePath));
		String[] headerDetails=br.readLine().split(",");
		String currentLine;
		while((currentLine=br.readLine())!=null){
			String[] userDetails=currentLine.split(",");
			if(userDetails[0].equals("\"\""))
				continue;

			String id=userDetails[0].substring(1,userDetails[0].length()-1);
			String firstName=userDetails[1].substring(1,userDetails[1].length()-1);
			String lastName=userDetails[2].substring(1,userDetails[2].length()-1);
			String userName=userDetails[3].substring(1,userDetails[3].length()-1);
			String email=userDetails[4].substring(1,userDetails[4].length()-1);
			String avatar=userDetails[5].substring(1,userDetails[5].length()-1);
			String gender=userDetails[6].substring(1,userDetails[6].length()-1);
			String dob=userDetails[7].substring(1,userDetails[7].length()-1);
			String address=userDetails[8].substring(1,userDetails[8].length()-1);
			csvData.add(new UserData(id,firstName,lastName,userName,email,avatar,gender,dob,address));

		}
		Collections.sort(csvData);

		File file2 = new File(sortedFilePath);
		try {
			FileWriter outputFile = new FileWriter(file2);
			CSVWriter writer = new CSVWriter(outputFile);
			writer.writeNext(headerDetails);
			for(int i=0;i<csvData.size();i++) {
				String[] data={ csvData.get(i).getId(), csvData.get(i).getFirstName(), csvData.get(i).getLastName(),csvData.get(i).getUserName(),csvData.get(i).getEmail(),csvData.get(i).getAvatar(),csvData.get(i).getGender(),csvData.get(i).getDob(),csvData.get(i).getAddress() };
				writer.writeNext(data);
			}
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("CSV file:"+filePath+" details have been sorted and stored in filepath:"+ sortedFilePath);
	}

	public void fetchAndPrintUserDetailsForGivenUserName(String requestedUserName,String filePath) throws IOException {
		UserData userData=null;
		BufferedReader br=new BufferedReader(new FileReader(filePath));
		String[] headerDetails=br.readLine().split(",");
		String currentLine;
		while((currentLine=br.readLine())!=null){
			String[] userDetails=currentLine.split(",");
			if(userDetails[0].equals("\"\""))
				continue;

			if(userDetails[3].substring(1,userDetails[3].length()-1).equals(requestedUserName)){
				String id=userDetails[0].substring(1,userDetails[0].length()-1);
				String firstName=userDetails[1].substring(1,userDetails[1].length()-1);
				String lastName=userDetails[2].substring(1,userDetails[2].length()-1);
				String userName=userDetails[3].substring(1,userDetails[3].length()-1);
				String email=userDetails[4].substring(1,userDetails[4].length()-1);
				String avatar=userDetails[5].substring(1,userDetails[5].length()-1);
				String gender=userDetails[6].substring(1,userDetails[6].length()-1);
				String dob=userDetails[7].substring(1,userDetails[7].length()-1);
				String address=userDetails[8].substring(1,userDetails[8].length()-1);
				userData=new UserData(id,firstName,lastName,userName,email,avatar,gender,dob,address);
			}
		}
		print(userData);
	}

	public void print(UserData userDetails){
		if(userDetails!=null)
		System.out.print("UserDetails:{"+ "Id:"+userDetails.getId()+",firstName:"+userDetails.getFirstName()+",lastName:"+userDetails.getLastName()+",userName:"+userDetails.getUserName()+",email:"+userDetails.getEmail()+",avatar:"+userDetails.getAvatar()+",gender:"+userDetails.getGender()+",dob:"+userDetails.getDob()+",address:"+userDetails.getAddress());

	}
}
