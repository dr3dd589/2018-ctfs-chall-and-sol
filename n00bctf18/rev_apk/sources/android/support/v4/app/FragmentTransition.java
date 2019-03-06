package android.support.v4.app;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;

class FragmentTransition {
    private static final int[] INVERSE_OPS = new int[]{0, 3, 0, 1, 5, 4, 7, 6, 9, 8};

    static class FragmentContainerTransition {
        public Fragment firstOut;
        public boolean firstOutIsPop;
        public BackStackRecord firstOutTransaction;
        public Fragment lastIn;
        public boolean lastInIsPop;
        public BackStackRecord lastInTransaction;

        FragmentContainerTransition() {
        }
    }

    FragmentTransition() {
    }

    static void startTransitions(FragmentManagerImpl fragmentManager, ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex, boolean isReordered) {
        if (fragmentManager.mCurState >= 1 && VERSION.SDK_INT >= 21) {
            SparseArray<FragmentContainerTransition> transitioningFragments = new SparseArray();
            for (int i = startIndex; i < endIndex; i++) {
                BackStackRecord record = (BackStackRecord) records.get(i);
                if (((Boolean) isRecordPop.get(i)).booleanValue()) {
                    calculatePopFragments(record, transitioningFragments, isReordered);
                } else {
                    calculateFragments(record, transitioningFragments, isReordered);
                }
            }
            if (transitioningFragments.size() != 0) {
                View nonExistentView = new View(fragmentManager.mHost.getContext());
                int numContainers = transitioningFragments.size();
                for (int i2 = 0; i2 < numContainers; i2++) {
                    int containerId = transitioningFragments.keyAt(i2);
                    ArrayMap<String, String> nameOverrides = calculateNameOverrides(containerId, records, isRecordPop, startIndex, endIndex);
                    FragmentContainerTransition containerTransition = (FragmentContainerTransition) transitioningFragments.valueAt(i2);
                    if (isReordered) {
                        configureTransitionsReordered(fragmentManager, containerId, containerTransition, nonExistentView, nameOverrides);
                    } else {
                        configureTransitionsOrdered(fragmentManager, containerId, containerTransition, nonExistentView, nameOverrides);
                    }
                }
            }
        }
    }

    private static ArrayMap<String, String> calculateNameOverrides(int containerId, ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex) {
        ArrayMap<String, String> nameOverrides = new ArrayMap();
        for (int recordNum = endIndex - 1; recordNum >= startIndex; recordNum--) {
            BackStackRecord record = (BackStackRecord) records.get(recordNum);
            if (record.interactsWith(containerId)) {
                boolean isPop = ((Boolean) isRecordPop.get(recordNum)).booleanValue();
                if (record.mSharedElementSourceNames != null) {
                    ArrayList<String> targets;
                    ArrayList<String> sources;
                    int numSharedElements = record.mSharedElementSourceNames.size();
                    if (isPop) {
                        targets = record.mSharedElementSourceNames;
                        sources = record.mSharedElementTargetNames;
                    } else {
                        sources = record.mSharedElementSourceNames;
                        targets = record.mSharedElementTargetNames;
                    }
                    for (int i = 0; i < numSharedElements; i++) {
                        String sourceName = (String) sources.get(i);
                        String targetName = (String) targets.get(i);
                        String previousTarget = (String) nameOverrides.remove(targetName);
                        if (previousTarget != null) {
                            nameOverrides.put(sourceName, previousTarget);
                        } else {
                            nameOverrides.put(sourceName, targetName);
                        }
                    }
                }
            }
        }
        return nameOverrides;
    }

    @RequiresApi(21)
    private static void configureTransitionsReordered(FragmentManagerImpl fragmentManager, int containerId, FragmentContainerTransition fragments, View nonExistentView, ArrayMap<String, String> nameOverrides) {
        FragmentManagerImpl fragmentManagerImpl = fragmentManager;
        FragmentContainerTransition fragmentContainerTransition = fragments;
        View view = nonExistentView;
        ViewGroup sceneRoot = null;
        if (fragmentManagerImpl.mContainer.onHasView()) {
            sceneRoot = (ViewGroup) fragmentManagerImpl.mContainer.onFindViewById(containerId);
        } else {
            int i = containerId;
        }
        ViewGroup sceneRoot2 = sceneRoot;
        if (sceneRoot2 != null) {
            Object exitTransition;
            Fragment inFragment = fragmentContainerTransition.lastIn;
            Fragment outFragment = fragmentContainerTransition.firstOut;
            boolean inIsPop = fragmentContainerTransition.lastInIsPop;
            boolean outIsPop = fragmentContainerTransition.firstOutIsPop;
            ArrayList<View> sharedElementsIn = new ArrayList();
            ArrayList<View> sharedElementsOut = new ArrayList();
            Object enterTransition = getEnterTransition(inFragment, inIsPop);
            Object exitTransition2 = getExitTransition(outFragment, outIsPop);
            Object enterTransition2 = enterTransition;
            ArrayList<View> sharedElementsOut2 = sharedElementsOut;
            ArrayList<View> sharedElementsIn2 = sharedElementsIn;
            Object sharedElementTransition = configureSharedElementsReordered(sceneRoot2, nonExistentView, nameOverrides, fragments, sharedElementsOut, sharedElementsIn, enterTransition2, exitTransition2);
            Object enterTransition3 = enterTransition2;
            if (enterTransition3 == null && sharedElementTransition == null) {
                exitTransition = exitTransition2;
                if (exitTransition == null) {
                    return;
                }
            }
            exitTransition = exitTransition2;
            ArrayList<View> exitingViews = configureEnteringExitingViews(exitTransition, outFragment, sharedElementsOut2, view);
            ArrayList<View> enteringViews = configureEnteringExitingViews(enterTransition3, inFragment, sharedElementsIn2, view);
            setViewVisibility(enteringViews, 4);
            Object transition = mergeTransitions(enterTransition3, exitTransition, sharedElementTransition, inFragment, inIsPop);
            if (transition != null) {
                replaceHide(exitTransition, outFragment, exitingViews);
                ArrayList<String> inNames = FragmentTransitionCompat21.prepareSetNameOverridesReordered(sharedElementsIn2);
                FragmentTransitionCompat21.scheduleRemoveTargets(transition, enterTransition3, enteringViews, exitTransition, exitingViews, sharedElementTransition, sharedElementsIn2);
                FragmentTransitionCompat21.beginDelayedTransition(sceneRoot2, transition);
                FragmentTransitionCompat21.setNameOverridesReordered(sceneRoot2, sharedElementsOut2, sharedElementsIn2, inNames, nameOverrides);
                setViewVisibility(enteringViews, 0);
                FragmentTransitionCompat21.swapSharedElementTargets(sharedElementTransition, sharedElementsOut2, sharedElementsIn2);
            } else {
                ArrayMap<String, String> arrayMap = nameOverrides;
                Object obj = enterTransition3;
            }
        }
    }

    @RequiresApi(21)
    private static void replaceHide(Object exitTransition, Fragment exitingFragment, final ArrayList<View> exitingViews) {
        if (exitingFragment != null && exitTransition != null && exitingFragment.mAdded && exitingFragment.mHidden && exitingFragment.mHiddenChanged) {
            exitingFragment.setHideReplaced(true);
            FragmentTransitionCompat21.scheduleHideFragmentView(exitTransition, exitingFragment.getView(), exitingViews);
            OneShotPreDrawListener.add(exitingFragment.mContainer, new Runnable() {
                public void run() {
                    FragmentTransition.setViewVisibility(exitingViews, 4);
                }
            });
        }
    }

    @RequiresApi(21)
    private static void configureTransitionsOrdered(FragmentManagerImpl fragmentManager, int containerId, FragmentContainerTransition fragments, View nonExistentView, ArrayMap<String, String> nameOverrides) {
        FragmentManagerImpl fragmentManagerImpl = fragmentManager;
        FragmentContainerTransition fragmentContainerTransition = fragments;
        View view = nonExistentView;
        ArrayMap<String, String> arrayMap = nameOverrides;
        ViewGroup sceneRoot = null;
        if (fragmentManagerImpl.mContainer.onHasView()) {
            sceneRoot = (ViewGroup) fragmentManagerImpl.mContainer.onFindViewById(containerId);
        } else {
            int i = containerId;
        }
        ViewGroup sceneRoot2 = sceneRoot;
        if (sceneRoot2 != null) {
            Object exitTransition;
            Fragment inFragment = fragmentContainerTransition.lastIn;
            Fragment outFragment = fragmentContainerTransition.firstOut;
            boolean inIsPop = fragmentContainerTransition.lastInIsPop;
            boolean outIsPop = fragmentContainerTransition.firstOutIsPop;
            Object enterTransition = getEnterTransition(inFragment, inIsPop);
            Object exitTransition2 = getExitTransition(outFragment, outIsPop);
            ArrayList<View> sharedElementsOut = new ArrayList();
            ArrayList<View> sharedElementsIn = new ArrayList();
            ArrayList<View> sharedElementsOut2 = sharedElementsOut;
            Object exitTransition3 = exitTransition2;
            Object enterTransition2 = enterTransition;
            Object sharedElementTransition = configureSharedElementsOrdered(sceneRoot2, nonExistentView, nameOverrides, fragments, sharedElementsOut2, sharedElementsIn, enterTransition2, exitTransition3);
            if (enterTransition2 == null && sharedElementTransition == null) {
                exitTransition = exitTransition3;
                if (exitTransition == null) {
                    return;
                }
            }
            exitTransition = exitTransition3;
            ArrayList<View> sharedElementsOut3 = sharedElementsOut2;
            sharedElementsOut2 = configureEnteringExitingViews(exitTransition, outFragment, sharedElementsOut3, view);
            if (sharedElementsOut2 == null || sharedElementsOut2.isEmpty()) {
                exitTransition = null;
            }
            exitTransition2 = exitTransition;
            FragmentTransitionCompat21.addTarget(enterTransition2, view);
            Object transition = mergeTransitions(enterTransition2, exitTransition2, sharedElementTransition, inFragment, fragmentContainerTransition.lastInIsPop);
            ArrayList<View> sharedElementsIn2;
            if (transition != null) {
                ArrayList<View> enteringViews = new ArrayList();
                FragmentTransitionCompat21.scheduleRemoveTargets(transition, enterTransition2, enteringViews, exitTransition2, sharedElementsOut2, sharedElementTransition, sharedElementsIn);
                Object transition2 = transition;
                scheduleTargetChange(sceneRoot2, inFragment, nonExistentView, sharedElementsIn, enterTransition2, enteringViews, exitTransition2, sharedElementsOut2);
                sharedElementsIn2 = sharedElementsIn;
                FragmentTransitionCompat21.setNameOverridesOrdered(sceneRoot2, sharedElementsIn2, arrayMap);
                FragmentTransitionCompat21.beginDelayedTransition(sceneRoot2, transition2);
                FragmentTransitionCompat21.scheduleNameReset(sceneRoot2, sharedElementsIn2, arrayMap);
            } else {
                Object obj = exitTransition2;
                ArrayList<View> arrayList = sharedElementsOut3;
                Object obj2 = enterTransition2;
                Object obj3 = sharedElementTransition;
                sharedElementsIn2 = sharedElementsIn;
            }
        }
    }

    @RequiresApi(21)
    private static void scheduleTargetChange(ViewGroup sceneRoot, Fragment inFragment, View nonExistentView, ArrayList<View> sharedElementsIn, Object enterTransition, ArrayList<View> enteringViews, Object exitTransition, ArrayList<View> exitingViews) {
        final Object obj = enterTransition;
        final View view = nonExistentView;
        final Fragment fragment = inFragment;
        final ArrayList<View> arrayList = sharedElementsIn;
        final ArrayList<View> arrayList2 = enteringViews;
        final ArrayList<View> arrayList3 = exitingViews;
        final Object obj2 = exitTransition;
        Runnable anonymousClass2 = new Runnable() {
            public void run() {
                if (obj != null) {
                    FragmentTransitionCompat21.removeTarget(obj, view);
                    arrayList2.addAll(FragmentTransition.configureEnteringExitingViews(obj, fragment, arrayList, view));
                }
                if (arrayList3 != null) {
                    if (obj2 != null) {
                        ArrayList<View> tempExiting = new ArrayList();
                        tempExiting.add(view);
                        FragmentTransitionCompat21.replaceTargets(obj2, arrayList3, tempExiting);
                    }
                    arrayList3.clear();
                    arrayList3.add(view);
                }
            }
        };
        ViewGroup viewGroup = sceneRoot;
        OneShotPreDrawListener.add(sceneRoot, anonymousClass2);
    }

    @RequiresApi(21)
    private static Object getSharedElementTransition(Fragment inFragment, Fragment outFragment, boolean isPop) {
        if (inFragment == null || outFragment == null) {
            return null;
        }
        Object transition;
        if (isPop) {
            transition = outFragment.getSharedElementReturnTransition();
        } else {
            transition = inFragment.getSharedElementEnterTransition();
        }
        return FragmentTransitionCompat21.wrapTransitionInSet(FragmentTransitionCompat21.cloneTransition(transition));
    }

    @RequiresApi(21)
    private static Object getEnterTransition(Fragment inFragment, boolean isPop) {
        if (inFragment == null) {
            return null;
        }
        Object reenterTransition;
        if (isPop) {
            reenterTransition = inFragment.getReenterTransition();
        } else {
            reenterTransition = inFragment.getEnterTransition();
        }
        return FragmentTransitionCompat21.cloneTransition(reenterTransition);
    }

    @RequiresApi(21)
    private static Object getExitTransition(Fragment outFragment, boolean isPop) {
        if (outFragment == null) {
            return null;
        }
        Object returnTransition;
        if (isPop) {
            returnTransition = outFragment.getReturnTransition();
        } else {
            returnTransition = outFragment.getExitTransition();
        }
        return FragmentTransitionCompat21.cloneTransition(returnTransition);
    }

    @RequiresApi(21)
    private static Object configureSharedElementsReordered(ViewGroup sceneRoot, View nonExistentView, ArrayMap<String, String> nameOverrides, FragmentContainerTransition fragments, ArrayList<View> sharedElementsOut, ArrayList<View> sharedElementsIn, Object enterTransition, Object exitTransition) {
        View view = nonExistentView;
        ArrayMap<String, String> arrayMap = nameOverrides;
        FragmentContainerTransition fragmentContainerTransition = fragments;
        ArrayList<View> arrayList = sharedElementsOut;
        ArrayList<View> arrayList2 = sharedElementsIn;
        Object obj = enterTransition;
        Object obj2 = exitTransition;
        Fragment inFragment = fragmentContainerTransition.lastIn;
        Fragment outFragment = fragmentContainerTransition.firstOut;
        if (inFragment != null) {
            inFragment.getView().setVisibility(0);
        }
        if (inFragment == null || outFragment == null) {
            ViewGroup viewGroup = sceneRoot;
            return null;
        }
        boolean inIsPop = fragmentContainerTransition.lastInIsPop;
        Object sharedElementTransition = nameOverrides.isEmpty() ? null : getSharedElementTransition(inFragment, outFragment, inIsPop);
        ArrayMap<String, View> outSharedElements = captureOutSharedElements(arrayMap, sharedElementTransition, fragmentContainerTransition);
        ArrayMap<String, View> inSharedElements = captureInSharedElements(arrayMap, sharedElementTransition, fragmentContainerTransition);
        if (nameOverrides.isEmpty()) {
            sharedElementTransition = null;
            if (outSharedElements != null) {
                outSharedElements.clear();
            }
            if (inSharedElements != null) {
                inSharedElements.clear();
            }
        } else {
            addSharedElementsWithMatchingNames(arrayList, outSharedElements, nameOverrides.keySet());
            addSharedElementsWithMatchingNames(arrayList2, inSharedElements, nameOverrides.values());
        }
        Object sharedElementTransition2 = sharedElementTransition;
        if (obj == null && obj2 == null && sharedElementTransition2 == null) {
            return null;
        }
        Rect epicenter;
        View epicenterView;
        callSharedElementStartEnd(inFragment, outFragment, inIsPop, outSharedElements, true);
        if (sharedElementTransition2 != null) {
            arrayList2.add(view);
            FragmentTransitionCompat21.setSharedElementTargets(sharedElementTransition2, view, arrayList);
            setOutEpicenter(sharedElementTransition2, obj2, outSharedElements, fragmentContainerTransition.firstOutIsPop, fragmentContainerTransition.firstOutTransaction);
            Rect epicenter2 = new Rect();
            View epicenterView2 = getInEpicenterView(inSharedElements, fragmentContainerTransition, obj, inIsPop);
            if (epicenterView2 != null) {
                FragmentTransitionCompat21.setEpicenter(obj, epicenter2);
            }
            View view2 = epicenterView2;
            epicenter = epicenter2;
            epicenterView = view2;
        } else {
            epicenterView = null;
            epicenter = null;
        }
        AnonymousClass3 anonymousClass3 = r7;
        final Fragment fragment = inFragment;
        Object sharedElementTransition3 = sharedElementTransition2;
        final Fragment fragment2 = outFragment;
        ArrayMap<String, View> inSharedElements2 = inSharedElements;
        final boolean z = inIsPop;
        outSharedElements = inSharedElements2;
        final Rect rect = epicenter;
        AnonymousClass3 anonymousClass32 = new Runnable() {
            public void run() {
                FragmentTransition.callSharedElementStartEnd(fragment, fragment2, z, outSharedElements, false);
                if (epicenterView != null) {
                    FragmentTransitionCompat21.getBoundsOnScreen(epicenterView, rect);
                }
            }
        };
        OneShotPreDrawListener.add(sceneRoot, anonymousClass3);
        return sharedElementTransition3;
    }

    private static void addSharedElementsWithMatchingNames(ArrayList<View> views, ArrayMap<String, View> sharedElements, Collection<String> nameOverridesSet) {
        for (int i = sharedElements.size() - 1; i >= 0; i--) {
            View view = (View) sharedElements.valueAt(i);
            if (nameOverridesSet.contains(ViewCompat.getTransitionName(view))) {
                views.add(view);
            }
        }
    }

    @RequiresApi(21)
    private static Object configureSharedElementsOrdered(ViewGroup sceneRoot, View nonExistentView, ArrayMap<String, String> nameOverrides, FragmentContainerTransition fragments, ArrayList<View> sharedElementsOut, ArrayList<View> sharedElementsIn, Object enterTransition, Object exitTransition) {
        FragmentContainerTransition fragmentContainerTransition = fragments;
        ArrayList<View> arrayList = sharedElementsOut;
        Object obj = enterTransition;
        Object obj2 = exitTransition;
        Fragment inFragment = fragmentContainerTransition.lastIn;
        Fragment outFragment = fragmentContainerTransition.firstOut;
        Rect rect = null;
        ViewGroup viewGroup;
        Fragment fragment;
        Fragment fragment2;
        if (inFragment == null) {
            viewGroup = sceneRoot;
            fragment = outFragment;
            fragment2 = inFragment;
        } else if (outFragment == null) {
            viewGroup = sceneRoot;
            fragment = outFragment;
            fragment2 = inFragment;
        } else {
            final boolean z = fragmentContainerTransition.lastInIsPop;
            Object sharedElementTransition = nameOverrides.isEmpty() ? null : getSharedElementTransition(inFragment, outFragment, z);
            ArrayMap<String, View> outSharedElements = captureOutSharedElements(nameOverrides, sharedElementTransition, fragmentContainerTransition);
            if (nameOverrides.isEmpty()) {
                sharedElementTransition = null;
            } else {
                arrayList.addAll(outSharedElements.values());
            }
            Object sharedElementTransition2 = sharedElementTransition;
            if (obj == null && obj2 == null && sharedElementTransition2 == null) {
                return null;
            }
            callSharedElementStartEnd(inFragment, outFragment, z, outSharedElements, true);
            if (sharedElementTransition2 != null) {
                rect = new Rect();
                FragmentTransitionCompat21.setSharedElementTargets(sharedElementTransition2, nonExistentView, arrayList);
                setOutEpicenter(sharedElementTransition2, obj2, outSharedElements, fragmentContainerTransition.firstOutIsPop, fragmentContainerTransition.firstOutTransaction);
                if (obj != null) {
                    FragmentTransitionCompat21.setEpicenter(obj, rect);
                }
            } else {
                View view = nonExistentView;
            }
            final Rect inEpicenter = rect;
            final Object finalSharedElementTransition = sharedElementTransition2;
            final ArrayMap<String, String> arrayMap = nameOverrides;
            AnonymousClass4 anonymousClass4 = r0;
            final FragmentContainerTransition fragmentContainerTransition2 = fragments;
            final ArrayList<View> arrayList2 = sharedElementsIn;
            Object sharedElementTransition3 = sharedElementTransition2;
            final View view2 = nonExistentView;
            final Fragment fragment3 = inFragment;
            final Fragment fragment4 = outFragment;
            boolean inIsPop = z;
            final ArrayList<View> arrayList3 = sharedElementsOut;
            final Object obj3 = enterTransition;
            AnonymousClass4 anonymousClass42 = new Runnable() {
                public void run() {
                    ArrayMap<String, View> inSharedElements = FragmentTransition.captureInSharedElements(arrayMap, finalSharedElementTransition, fragmentContainerTransition2);
                    if (inSharedElements != null) {
                        arrayList2.addAll(inSharedElements.values());
                        arrayList2.add(view2);
                    }
                    FragmentTransition.callSharedElementStartEnd(fragment3, fragment4, z, inSharedElements, false);
                    if (finalSharedElementTransition != null) {
                        FragmentTransitionCompat21.swapSharedElementTargets(finalSharedElementTransition, arrayList3, arrayList2);
                        View inEpicenterView = FragmentTransition.getInEpicenterView(inSharedElements, fragmentContainerTransition2, obj3, z);
                        if (inEpicenterView != null) {
                            FragmentTransitionCompat21.getBoundsOnScreen(inEpicenterView, inEpicenter);
                        }
                    }
                }
            };
            OneShotPreDrawListener.add(sceneRoot, anonymousClass4);
            return sharedElementTransition3;
        }
        return null;
    }

    @RequiresApi(21)
    private static ArrayMap<String, View> captureOutSharedElements(ArrayMap<String, String> nameOverrides, Object sharedElementTransition, FragmentContainerTransition fragments) {
        if (nameOverrides.isEmpty() || sharedElementTransition == null) {
            nameOverrides.clear();
            return null;
        }
        SharedElementCallback sharedElementCallback;
        ArrayList<String> names;
        Fragment outFragment = fragments.firstOut;
        ArrayMap<String, View> outSharedElements = new ArrayMap();
        FragmentTransitionCompat21.findNamedViews(outSharedElements, outFragment.getView());
        BackStackRecord outTransaction = fragments.firstOutTransaction;
        if (fragments.firstOutIsPop) {
            sharedElementCallback = outFragment.getEnterTransitionCallback();
            names = outTransaction.mSharedElementTargetNames;
        } else {
            sharedElementCallback = outFragment.getExitTransitionCallback();
            names = outTransaction.mSharedElementSourceNames;
        }
        outSharedElements.retainAll(names);
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(names, outSharedElements);
            for (int i = names.size() - 1; i >= 0; i--) {
                String name = (String) names.get(i);
                View view = (View) outSharedElements.get(name);
                if (view == null) {
                    nameOverrides.remove(name);
                } else if (!name.equals(ViewCompat.getTransitionName(view))) {
                    nameOverrides.put(ViewCompat.getTransitionName(view), (String) nameOverrides.remove(name));
                }
            }
        } else {
            nameOverrides.retainAll(outSharedElements.keySet());
        }
        return outSharedElements;
    }

    @RequiresApi(21)
    private static ArrayMap<String, View> captureInSharedElements(ArrayMap<String, String> nameOverrides, Object sharedElementTransition, FragmentContainerTransition fragments) {
        Fragment inFragment = fragments.lastIn;
        View fragmentView = inFragment.getView();
        if (nameOverrides.isEmpty() || sharedElementTransition == null || fragmentView == null) {
            nameOverrides.clear();
            return null;
        }
        SharedElementCallback sharedElementCallback;
        ArrayList<String> names;
        ArrayMap<String, View> inSharedElements = new ArrayMap();
        FragmentTransitionCompat21.findNamedViews(inSharedElements, fragmentView);
        BackStackRecord inTransaction = fragments.lastInTransaction;
        if (fragments.lastInIsPop) {
            sharedElementCallback = inFragment.getExitTransitionCallback();
            names = inTransaction.mSharedElementSourceNames;
        } else {
            sharedElementCallback = inFragment.getEnterTransitionCallback();
            names = inTransaction.mSharedElementTargetNames;
        }
        if (names != null) {
            inSharedElements.retainAll(names);
        }
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(names, inSharedElements);
            for (int i = names.size() - 1; i >= 0; i--) {
                String name = (String) names.get(i);
                View view = (View) inSharedElements.get(name);
                String key;
                if (view == null) {
                    key = findKeyForValue(nameOverrides, name);
                    if (key != null) {
                        nameOverrides.remove(key);
                    }
                } else if (!name.equals(ViewCompat.getTransitionName(view))) {
                    key = findKeyForValue(nameOverrides, name);
                    if (key != null) {
                        nameOverrides.put(key, ViewCompat.getTransitionName(view));
                    }
                }
            }
        } else {
            retainValues(nameOverrides, inSharedElements);
        }
        return inSharedElements;
    }

    private static String findKeyForValue(ArrayMap<String, String> map, String value) {
        int numElements = map.size();
        for (int i = 0; i < numElements; i++) {
            if (value.equals(map.valueAt(i))) {
                return (String) map.keyAt(i);
            }
        }
        return null;
    }

    private static View getInEpicenterView(ArrayMap<String, View> inSharedElements, FragmentContainerTransition fragments, Object enterTransition, boolean inIsPop) {
        BackStackRecord inTransaction = fragments.lastInTransaction;
        if (enterTransition == null || inSharedElements == null || inTransaction.mSharedElementSourceNames == null || inTransaction.mSharedElementSourceNames.isEmpty()) {
            return null;
        }
        String targetName;
        if (inIsPop) {
            targetName = (String) inTransaction.mSharedElementSourceNames.get(0);
        } else {
            targetName = (String) inTransaction.mSharedElementTargetNames.get(0);
        }
        return (View) inSharedElements.get(targetName);
    }

    @RequiresApi(21)
    private static void setOutEpicenter(Object sharedElementTransition, Object exitTransition, ArrayMap<String, View> outSharedElements, boolean outIsPop, BackStackRecord outTransaction) {
        if (outTransaction.mSharedElementSourceNames != null && !outTransaction.mSharedElementSourceNames.isEmpty()) {
            String sourceName;
            if (outIsPop) {
                sourceName = (String) outTransaction.mSharedElementTargetNames.get(0);
            } else {
                sourceName = (String) outTransaction.mSharedElementSourceNames.get(0);
            }
            View outEpicenterView = (View) outSharedElements.get(sourceName);
            FragmentTransitionCompat21.setEpicenter(sharedElementTransition, outEpicenterView);
            if (exitTransition != null) {
                FragmentTransitionCompat21.setEpicenter(exitTransition, outEpicenterView);
            }
        }
    }

    private static void retainValues(ArrayMap<String, String> nameOverrides, ArrayMap<String, View> namedViews) {
        for (int i = nameOverrides.size() - 1; i >= 0; i--) {
            if (!namedViews.containsKey((String) nameOverrides.valueAt(i))) {
                nameOverrides.removeAt(i);
            }
        }
    }

    private static void callSharedElementStartEnd(Fragment inFragment, Fragment outFragment, boolean isPop, ArrayMap<String, View> sharedElements, boolean isStart) {
        SharedElementCallback sharedElementCallback;
        if (isPop) {
            sharedElementCallback = outFragment.getEnterTransitionCallback();
        } else {
            sharedElementCallback = inFragment.getEnterTransitionCallback();
        }
        if (sharedElementCallback != null) {
            ArrayList<View> views = new ArrayList();
            ArrayList<String> names = new ArrayList();
            int i = 0;
            int count = sharedElements == null ? 0 : sharedElements.size();
            while (i < count) {
                names.add(sharedElements.keyAt(i));
                views.add(sharedElements.valueAt(i));
                i++;
            }
            if (isStart) {
                sharedElementCallback.onSharedElementStart(names, views, null);
            } else {
                sharedElementCallback.onSharedElementEnd(names, views, null);
            }
        }
    }

    @RequiresApi(21)
    private static ArrayList<View> configureEnteringExitingViews(Object transition, Fragment fragment, ArrayList<View> sharedElements, View nonExistentView) {
        ArrayList<View> viewList = null;
        if (transition != null) {
            viewList = new ArrayList();
            View root = fragment.getView();
            if (root != null) {
                FragmentTransitionCompat21.captureTransitioningViews(viewList, root);
            }
            if (sharedElements != null) {
                viewList.removeAll(sharedElements);
            }
            if (!viewList.isEmpty()) {
                viewList.add(nonExistentView);
                FragmentTransitionCompat21.addTargets(transition, viewList);
            }
        }
        return viewList;
    }

    private static void setViewVisibility(ArrayList<View> views, int visibility) {
        if (views != null) {
            for (int i = views.size() - 1; i >= 0; i--) {
                ((View) views.get(i)).setVisibility(visibility);
            }
        }
    }

    @RequiresApi(21)
    private static Object mergeTransitions(Object enterTransition, Object exitTransition, Object sharedElementTransition, Fragment inFragment, boolean isPop) {
        boolean overlap = true;
        if (!(enterTransition == null || exitTransition == null || inFragment == null)) {
            boolean allowReturnTransitionOverlap;
            if (isPop) {
                allowReturnTransitionOverlap = inFragment.getAllowReturnTransitionOverlap();
            } else {
                allowReturnTransitionOverlap = inFragment.getAllowEnterTransitionOverlap();
            }
            overlap = allowReturnTransitionOverlap;
        }
        if (overlap) {
            return FragmentTransitionCompat21.mergeTransitionsTogether(exitTransition, enterTransition, sharedElementTransition);
        }
        return FragmentTransitionCompat21.mergeTransitionsInSequence(exitTransition, enterTransition, sharedElementTransition);
    }

    public static void calculateFragments(BackStackRecord transaction, SparseArray<FragmentContainerTransition> transitioningFragments, boolean isReordered) {
        int numOps = transaction.mOps.size();
        for (int opNum = 0; opNum < numOps; opNum++) {
            addToFirstInLastOut(transaction, (Op) transaction.mOps.get(opNum), transitioningFragments, false, isReordered);
        }
    }

    public static void calculatePopFragments(BackStackRecord transaction, SparseArray<FragmentContainerTransition> transitioningFragments, boolean isReordered) {
        if (transaction.mManager.mContainer.onHasView()) {
            for (int opNum = transaction.mOps.size() - 1; opNum >= 0; opNum--) {
                addToFirstInLastOut(transaction, (Op) transaction.mOps.get(opNum), transitioningFragments, true, isReordered);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:91:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x00f2  */
    private static void addToFirstInLastOut(android.support.v4.app.BackStackRecord r22, android.support.v4.app.BackStackRecord.Op r23, android.util.SparseArray<android.support.v4.app.FragmentTransition.FragmentContainerTransition> r24, boolean r25, boolean r26) {
        /*
        r0 = r22;
        r1 = r23;
        r2 = r24;
        r3 = r25;
        r10 = r1.fragment;
        if (r10 != 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r11 = r10.mContainerId;
        if (r11 != 0) goto L_0x0012;
    L_0x0011:
        return;
    L_0x0012:
        if (r3 == 0) goto L_0x001b;
    L_0x0014:
        r4 = INVERSE_OPS;
        r5 = r1.cmd;
        r4 = r4[r5];
        goto L_0x001d;
    L_0x001b:
        r4 = r1.cmd;
    L_0x001d:
        r12 = r4;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 1;
        if (r12 == r9) goto L_0x008f;
    L_0x0026:
        switch(r12) {
            case 3: goto L_0x0065;
            case 4: goto L_0x0046;
            case 5: goto L_0x0030;
            case 6: goto L_0x0065;
            case 7: goto L_0x008f;
            default: goto L_0x0029;
        };
    L_0x0029:
        r13 = r4;
        r15 = r5;
        r16 = r6;
        r14 = r7;
        goto L_0x00a1;
    L_0x0030:
        if (r26 == 0) goto L_0x0042;
    L_0x0032:
        r13 = r10.mHiddenChanged;
        if (r13 == 0) goto L_0x0040;
    L_0x0036:
        r13 = r10.mHidden;
        if (r13 != 0) goto L_0x0040;
    L_0x003a:
        r13 = r10.mAdded;
        if (r13 == 0) goto L_0x0040;
    L_0x003e:
        r8 = 1;
    L_0x0040:
        r4 = r8;
        goto L_0x0044;
    L_0x0042:
        r4 = r10.mHidden;
    L_0x0044:
        r7 = 1;
        goto L_0x0029;
    L_0x0046:
        if (r26 == 0) goto L_0x0058;
    L_0x0048:
        r13 = r10.mHiddenChanged;
        if (r13 == 0) goto L_0x0056;
    L_0x004c:
        r13 = r10.mAdded;
        if (r13 == 0) goto L_0x0056;
    L_0x0050:
        r13 = r10.mHidden;
        if (r13 == 0) goto L_0x0056;
    L_0x0054:
        r8 = 1;
    L_0x0056:
        r6 = r8;
        goto L_0x0063;
    L_0x0058:
        r13 = r10.mAdded;
        if (r13 == 0) goto L_0x0062;
    L_0x005c:
        r13 = r10.mHidden;
        if (r13 != 0) goto L_0x0062;
    L_0x0060:
        r8 = 1;
    L_0x0062:
        r6 = r8;
    L_0x0063:
        r5 = 1;
        goto L_0x0029;
    L_0x0065:
        if (r26 == 0) goto L_0x0082;
    L_0x0067:
        r13 = r10.mAdded;
        if (r13 != 0) goto L_0x0080;
    L_0x006b:
        r13 = r10.mView;
        if (r13 == 0) goto L_0x0080;
    L_0x006f:
        r13 = r10.mView;
        r13 = r13.getVisibility();
        if (r13 != 0) goto L_0x0080;
    L_0x0077:
        r13 = r10.mPostponedAlpha;
        r14 = 0;
        r13 = (r13 > r14 ? 1 : (r13 == r14 ? 0 : -1));
        if (r13 < 0) goto L_0x0080;
    L_0x007e:
        r8 = 1;
    L_0x0080:
        r6 = r8;
        goto L_0x008d;
    L_0x0082:
        r13 = r10.mAdded;
        if (r13 == 0) goto L_0x008c;
    L_0x0086:
        r13 = r10.mHidden;
        if (r13 != 0) goto L_0x008c;
    L_0x008a:
        r8 = 1;
    L_0x008c:
        r6 = r8;
    L_0x008d:
        r5 = 1;
        goto L_0x0029;
    L_0x008f:
        if (r26 == 0) goto L_0x0094;
    L_0x0091:
        r4 = r10.mIsNewlyAdded;
        goto L_0x009f;
    L_0x0094:
        r13 = r10.mAdded;
        if (r13 != 0) goto L_0x009e;
    L_0x0098:
        r13 = r10.mHidden;
        if (r13 != 0) goto L_0x009e;
    L_0x009c:
        r8 = 1;
    L_0x009e:
        r4 = r8;
    L_0x009f:
        r7 = 1;
        goto L_0x0029;
    L_0x00a1:
        r4 = r2.get(r11);
        r4 = (android.support.v4.app.FragmentTransition.FragmentContainerTransition) r4;
        if (r13 == 0) goto L_0x00b4;
        r4 = ensureContainer(r4, r2, r11);
        r4.lastIn = r10;
        r4.lastInIsPop = r3;
        r4.lastInTransaction = r0;
    L_0x00b4:
        r8 = r4;
        r7 = 0;
        if (r26 != 0) goto L_0x00ed;
    L_0x00b8:
        if (r14 == 0) goto L_0x00ed;
    L_0x00ba:
        if (r8 == 0) goto L_0x00c2;
    L_0x00bc:
        r4 = r8.firstOut;
        if (r4 != r10) goto L_0x00c2;
    L_0x00c0:
        r8.firstOut = r7;
    L_0x00c2:
        r6 = r0.mManager;
        r4 = r10.mState;
        if (r4 >= r9) goto L_0x00ed;
    L_0x00c8:
        r4 = r6.mCurState;
        if (r4 < r9) goto L_0x00ed;
    L_0x00cc:
        r4 = r0.mReorderingAllowed;
        if (r4 != 0) goto L_0x00ed;
    L_0x00d0:
        r6.makeActive(r10);
        r9 = 1;
        r17 = 0;
        r18 = 0;
        r19 = 0;
        r4 = r6;
        r5 = r10;
        r20 = r6;
        r6 = r9;
        r9 = r7;
        r7 = r17;
        r21 = r8;
        r8 = r18;
        r1 = r9;
        r9 = r19;
        r4.moveToState(r5, r6, r7, r8, r9);
        goto L_0x00f0;
    L_0x00ed:
        r1 = r7;
        r21 = r8;
    L_0x00f0:
        if (r16 == 0) goto L_0x0107;
    L_0x00f2:
        r4 = r21;
        if (r4 == 0) goto L_0x00fa;
    L_0x00f6:
        r5 = r4.firstOut;
        if (r5 != 0) goto L_0x0109;
        r8 = ensureContainer(r4, r2, r11);
        r8.firstOut = r10;
        r8.firstOutIsPop = r3;
        r8.firstOutTransaction = r0;
        r4 = r8;
        goto L_0x0109;
    L_0x0107:
        r4 = r21;
    L_0x0109:
        if (r26 != 0) goto L_0x0115;
    L_0x010b:
        if (r15 == 0) goto L_0x0115;
    L_0x010d:
        if (r4 == 0) goto L_0x0115;
    L_0x010f:
        r5 = r4.lastIn;
        if (r5 != r10) goto L_0x0115;
    L_0x0113:
        r4.lastIn = r1;
    L_0x0115:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentTransition.addToFirstInLastOut(android.support.v4.app.BackStackRecord, android.support.v4.app.BackStackRecord$Op, android.util.SparseArray, boolean, boolean):void");
    }

    private static FragmentContainerTransition ensureContainer(FragmentContainerTransition containerTransition, SparseArray<FragmentContainerTransition> transitioningFragments, int containerId) {
        if (containerTransition != null) {
            return containerTransition;
        }
        containerTransition = new FragmentContainerTransition();
        transitioningFragments.put(containerId, containerTransition);
        return containerTransition;
    }
}
