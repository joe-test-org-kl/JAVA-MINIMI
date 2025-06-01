package com.example.library.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private Long id;
    private Book book;
    private Member member;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean returned;
    private boolean renewed;
    private int renewalCount;
    private double fineAmount;
    private String notes;

    public Loan() {
        this.returned = false;
        this.renewed = false;
        this.renewalCount = 0;
        this.fineAmount = 0.0;
    }

    public Loan(Long id, Book book, Member member, LocalDate loanDate, LocalDate dueDate) {
        this.id = id;
        this.book = book;
        this.member = member;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = false;
        this.renewed = false;
        this.renewalCount = 0;
        this.fineAmount = 0.0;
    }

    // Business methods
    public boolean isOverdue() {
        return !returned && LocalDate.now().isAfter(dueDate);
    }

    public long getDaysOverdue() {
        if (!isOverdue()) {
            return 0;
        }
        return ChronoUnit.DAYS.between(dueDate, LocalDate.now());
    }

    public double calculateFine() {
        if (!isOverdue()) {
            return 0.0;
        }
        long daysOverdue = getDaysOverdue();
        return daysOverdue * 0.50; // $0.50 per day fine
    }

    public boolean canRenew() {
        return !returned && renewalCount < 2 && !isOverdue();
    }

    public void renewLoan(int days) {
        if (canRenew()) {
            this.dueDate = this.dueDate.plusDays(days);
            this.renewed = true;
            this.renewalCount++;
        }
    }

    public void returnBook() {
        this.returned = true;
        this.returnDate = LocalDate.now();
        this.fineAmount = calculateFine();
        if (book != null) {
            book.setAvailable(true);
        }
    }

    public long getLoanDuration() {
        LocalDate endDate = returned ? returnDate : LocalDate.now();
        return ChronoUnit.DAYS.between(loanDate, endDate);
    }

    public String getLoanStatus() {
        if (returned) {
            return "Returned";
        } else if (isOverdue()) {
            return "Overdue";
        } else {
            return "Active";
        }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public boolean isReturned() { return returned; }
    public void setReturned(boolean returned) { this.returned = returned; }

    public boolean isRenewed() { return renewed; }
    public void setRenewed(boolean renewed) { this.renewed = renewed; }

    public int getRenewalCount() { return renewalCount; }
    public void setRenewalCount(int renewalCount) { this.renewalCount = renewalCount; }

    public double getFineAmount() { return fineAmount; }
    public void setFineAmount(double fineAmount) { this.fineAmount = fineAmount; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
} 