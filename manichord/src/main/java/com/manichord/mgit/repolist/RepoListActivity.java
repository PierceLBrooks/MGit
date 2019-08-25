package com.manichord.mgit.repolist;

import com.manichord.mgit.ViewHelperKt;

import me.sheimi.android.views.SheimiDialogFragment;
import me.sheimi.sgit.activities.RepoDetailActivity;
import me.sheimi.sgit.activities.UserSettingsActivity;
import me.sheimi.sgit.activities.explorer.ExploreFileActivity;
import me.sheimi.sgit.activities.explorer.FileExplorerActivity;
import me.sheimi.sgit.activities.explorer.ImportRepositoryActivity;
import me.sheimi.sgit.dialogs.ImportLocalRepoDialog;

public class RepoListActivity extends RepoListActivityBase {
    @Override
    protected void hideCloneView() {
        getBinding().getCloneViewModel().show(false);
        ViewHelperKt.hideKeyboard(this);
    }

    @Override
    protected String getResultPath() {
        return FileExplorerActivity.RESULT_PATH;
    }

    @Override
    protected String getFromPath() {
        return ImportLocalRepoDialog.FROM_PATH;
    }

    @Override
    protected Class<?> getRepoDetailActivityClass() {
        return RepoDetailActivity.class;
    }

    @Override
    protected Class<?> getImportRepositoryActivityClass() {
        return ImportRepositoryActivity.class;
    }

    @Override
    protected Class<?> getUserSettingsActivityClass() {
        return UserSettingsActivity.class;
    }

    @Override
    protected SheimiDialogFragment getImportLocalRepoDialog() {
        return new ImportLocalRepoDialog();
    }
}
