package test.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import test.dao.BirthDao;
import test.dao.EplDao;
import test.dto.BirthDto;
import test.dto.EplDto;

public class EplFrame extends JFrame 
				implements ActionListener,PropertyChangeListener{
	JTextField inputRank, inputTeam, inputPoint;
	DefaultTableModel model;
	JTable table;
	
	public EplFrame() {
		setLayout(new BorderLayout());
		
		JLabel label1=new JLabel("Rank");
		inputRank=new JTextField(10);
		
		JLabel label2=new JLabel("Team");
		inputTeam=new JTextField(10);
		
		JLabel label3=new JLabel("Point");
		inputPoint=new JTextField(10);
		
		JButton saveBtn=new JButton("save");
		saveBtn.setActionCommand("save");
		saveBtn.addActionListener(this);
		
		JButton deleteBtn=new JButton("delete");
		deleteBtn.setActionCommand("delete");
		deleteBtn.addActionListener(this);
		
		JPanel panel=new JPanel();
		panel.add(label1);
		panel.add(inputRank);
		panel.add(label2);
		panel.add(inputTeam);
		panel.add(label3);
		panel.add(inputPoint);
		
		panel.add(saveBtn);
		panel.add(deleteBtn);
		
		add(panel, BorderLayout.NORTH);
		
		table=new JTable();
		String[] colNames= {"Rank", "Team", "Point"};
		model=new DefaultTableModel(colNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column==0) {
					return false;
				}
				return true;
			}
		};
		table.setModel(model);
		JScrollPane scroll=new JScrollPane(table);
		add(scroll, BorderLayout.CENTER);
		displayMember();
		table.addPropertyChangeListener(this);
	}
	public void displayMember() {
		model.setRowCount(0);
		EplDao dao=EplDao.getInstance();
		List<EplDto> list=dao.getList();
		for(EplDto tmp:list) {
			Object[] row= {tmp.getRank(), tmp.getTeam(), tmp.getPoint()};
			model.addRow(row);
		}
	}
	
	public static void main(String[] args) {
		birthFrame f=new birthFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 800, 500);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if(command.equals("save")) {
			int rank= Integer.parseInt(inputRank.getText());
			String team=inputTeam.getText();
			int point=Integer.parseInt(inputPoint.getText());
			EplDto dto=new EplDto();
			dto.setRank(rank);
			dto.setTeam(team);
			dto.setPoint(point);
			EplDao dao=EplDao.getInstance();
			boolean isSuccess=dao.insert(dto);
			if(isSuccess) {
				JOptionPane.showMessageDialog(this, team+" 정보 추가 했습니다.");
			}else {
				JOptionPane.showMessageDialog(this, "추가 실패!");
			}
			displayMember();
		}else if(command.equals("delete")) {
			int selectedIndex=table.getSelectedRow();
			if(selectedIndex==-1) {
				return;
			}
			
			int selection=JOptionPane.showConfirmDialog(this, "선택된 row 를 삭제하겠습니까?");
			if(selection != JOptionPane.YES_OPTION) {
				return;
			}
			int num=(int)model.getValueAt(selectedIndex, 0);
			EplDao dao=EplDao.getInstance();
			dao.delete(num);
			displayMember();
		}
	}
	boolean isEditing=false;
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("property change!");
		System.out.println(evt.getPropertyName());
		if(evt.getPropertyName().equals("tableCellEditor")) {
			if(isEditing) {
				int selectedIndex=table.getSelectedRow();
				int rank=(int)model.getValueAt(selectedIndex, 0);
				String team=(String)model.getValueAt(selectedIndex, 1);
				int point=(int)model.getValueAt(selectedIndex, 2);
				EplDto dto=new EplDto(rank,team,point);
				EplDao.getInstance().update(dto);
				isEditing=false;
			}
			isEditing=true;
		}
	}
}