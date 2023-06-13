package com.yedam.phonebook;

//PhoneInfo: 이름,연락처  두개의 자식클래스를 가지는 부모클래스
//PhoneCompanyInfo: 회사 포함
//PhoneUnivInfo:학교포함
//PhoneBookManager: 기능포함 (메서드)
//예외사항 처리하는 MenuChoiceException, 메뉴: INIT_MENU,INPUT_SELECT
//메뉴출력해주는클래스 : MenuViewer
public class PhonebookApp {

	public static void main(String[] args) {
		PhoneBookManager app = PhoneBookManager.getInstance();
		int menu;
		//id,pass:id와passwd를 입력하세요
		//UserCheck
		UserCheck check = new UserCheck();
		while(true) {
			System.out.println("id와 pwd를 입력>");
			String val = MenuViewer.sc.next();
			String[] record = val.split(" ");
			if(check.loginCheck(record[0],record[1])) {
				break;
			}
		}
		
		while (true) {
			try {

				MenuViewer.showMenu();
				menu = MenuViewer.sc.nextInt();
				MenuViewer.sc.nextLine();
				if (menu < INIT_MENU.INPUT || menu > INIT_MENU.EXIT) {
					throw new MenuChoiceException(menu);
				}
				
				switch(menu) {
				case INIT_MENU.INPUT:
					app.inputData();
					break;
				case INIT_MENU.SEARCH:
					app.searchData();
					break;
				case INIT_MENU.DELETE:
					app.deleteData();
					break;
				case INIT_MENU.EXIT:
					app.storeToFile();
					return;
					
				}
			} catch (MenuChoiceException e) {
				e.showWrongChoice();
			}
			
		}
	}

}
