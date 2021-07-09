package com.example.bookmanager.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.bookmanager.domain.Book;

import org.jetbrains.annotations.NotNull;

public class DeleteDialog extends DialogFragment implements DialogInterface.OnClickListener {

    private Book book;
    private OnDeleteListener listener;

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        if(!(context instanceof  OnDeleteListener)){
            throw new RuntimeException("Não é um OnDeleteListener");
        }
        this.listener = (OnDeleteListener) context;
    }

    public Dialog onCreateDialog(Bundle saveInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("You want to remove book "+book.getTitle());
        builder.setPositiveButton("Yes", this);
        builder.setNegativeButton("No", this);
        return builder.create();
        //return super.onCreateDialog(saveInstanceState)
    }

    public void setBook(Book book){
        this.book = book;
    }

    @Override
    public void onClick(DialogInterface dialog, int i) {
        if(i == DialogInterface.BUTTON_POSITIVE){
            listener.onDelete(book);
        }
    }

    public interface OnDeleteListener {
        void onDelete(Book book);
    }
}
